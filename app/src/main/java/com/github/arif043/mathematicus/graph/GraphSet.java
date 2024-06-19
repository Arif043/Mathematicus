package com.github.arif043.mathematicus.graph;

import static android.view.ViewGroup.LayoutParams;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.LinkedList;

import com.github.arif043.mathematicus.FragmentUtils;
import com.github.arif043.mathematicus.R;
import com.github.arif043.mathematicus.keyboard.InactiveKeyboard;
import com.github.arif043.mathematicus.keyboard.Keyboard;
import ertugrul.arif.rechner.Function;

// Diese Klasse zeigt eine Table an, in dem die Funktionen eingetragen werden
public class GraphSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_set);

        int[] colors = getResources().getIntArray(R.array.functions_color);

        final EditText[] functionField = new EditText[20];
        LinearLayout functionLayout = findViewById(R.id.function_layout);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.remove(GraphSet.this, Keyboard.TAG);
                //Neues Fragment (Keyboard) hinzufügen
                FragmentTransaction transactionAdd = getSupportFragmentManager().beginTransaction();
                InactiveKeyboard keyboard = new InactiveKeyboard();
                Bundle bundle = new Bundle();
                bundle.putInt("id", R.id.draw);
                keyboard.setArguments(bundle);
                keyboard.setTextField((EditText) v);
                transactionAdd.add(R.id.hook, keyboard, Keyboard.TAG).
                        addToBackStack(null).commit();
                findViewById(R.id.draw).setVisibility(View.INVISIBLE);
            }
        };

        // Hier wird die Oberfläche programmtechnisch aufgebaut und eingebunden.
        for (int i = 0; i < 20; i++) {
            LinearLayout subLayout = new LinearLayout(this, null);
            subLayout.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            TextView text = new TextView(this, null);
            text.setText("Y" + (i +1) + ":");
            text.setTextSize(32);
            text.setTextColor(colors[i % colors.length]);
            functionField[i] = new EditText(this);
            functionField[i].setTextColor(colors[i % colors.length]);
            functionField[i].setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            functionField[i].setInputType(0);
            functionField[i].setOnClickListener(listener);
            subLayout.addView(text);
            subLayout.addView(functionField[i]);
            functionLayout.addView(subLayout);

        }
        /*
            Wenn der Anwender auf den Draw-Button drückt, werden die
            Funktionen gezeichnet in dem der GraphViewer gestartet wird
         */
        findViewById(R.id.draw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedList<Function> functions = new LinkedList<>();
                for (EditText field : functionField)
                    if (!field.getText().toString().isEmpty()) functions.add(new Function(field.getText().toString()));
                Intent intent = new Intent(GraphSet.this, GraphViewer.class);
                intent.putExtra("functions", functions.toArray(new Function[functions.size()]));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            //Wenn der Anwender auf die Zurück-Taste drückt, wird der Draw-Button sichtbar
            findViewById(R.id.draw).setVisibility(View.VISIBLE);
        return super.onKeyDown(keyCode, event);
    }

    //Methode zur Manipulation von Farben
    public int manipulateColor(int color, float factor){
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a, r, g, b);
    }
}