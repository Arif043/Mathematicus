package com.github.arif043.mathematicus.table;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.github.arif043.mathematicus.FragmentUtils;
import com.github.arif043.mathematicus.R;
import com.github.arif043.mathematicus.keyboard.InactiveKeyboard;
import com.github.arif043.mathematicus.keyboard.Keyboard;

public class TableSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_set);

        View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    FragmentUtils.remove(TableSet.this, Keyboard.TAG);

                    //Neues Fragment (Keyboard) hinzufügen
                    FragmentTransaction transactionAdd = getSupportFragmentManager().beginTransaction();
                    InactiveKeyboard keyboard = new InactiveKeyboard();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", R.id.calculate);
                    keyboard.setArguments(bundle);
                    keyboard.setTextField((EditText) v);
                    transactionAdd.add(R.id.hook1, keyboard, Keyboard.TAG).
                            addToBackStack(null).commit();
                    findViewById(R.id.calculate).setVisibility(View.INVISIBLE);
                }
            }
        };
        findViewById(R.id.y1).setOnFocusChangeListener(listener);
        findViewById(R.id.y2).setOnFocusChangeListener(listener);
        findViewById(R.id.y3).setOnFocusChangeListener(listener);
        ((EditText) findViewById(R.id.y1)).setInputType(0);
        ((EditText) findViewById(R.id.y2)).setInputType(0);
        ((EditText) findViewById(R.id.y3)).setInputType(0);

        findViewById(R.id.calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText start = findViewById(R.id.start);
                EditText delta = findViewById(R.id.delta);
                EditText end = findViewById(R.id.end);
                Bundle bundle = new Bundle();
                boolean minOneFunctionAdded = addFunction(bundle, ((EditText) findViewById(R.id.y1)).getText().toString(), 0, android.R.color.holo_red_dark)
                        | addFunction(bundle, ((EditText) findViewById(R.id.y2)).getText().toString(), 1, android.R.color.holo_blue_dark)
                        | addFunction(bundle, ((EditText) findViewById(R.id.y3)).getText().toString(), 2, android.R.color.holo_orange_dark);

                if (!start.getText().toString().isEmpty() && !delta.getText().toString().equals("") &&
                        !end.getText().toString().isEmpty() && minOneFunctionAdded) {

                    Intent intent = new Intent(TableSet.this, Table.class);

                    bundle.putDouble("start", Double.parseDouble(start.getText().toString()));
                    bundle.putDouble("delta", Double.parseDouble(delta.getText().toString()));
                    bundle.putDouble("end", Double.parseDouble(end.getText().toString()));

                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                else {
                    String msg = "";
                    if (start.getText().toString().isEmpty()) msg += "Geben Sie einen Startwert ein";
                    else if (delta.getText().toString().isEmpty()) msg += "Geben Sie einen Deltawert ein";
                    else if (end.getText().toString().isEmpty()) msg += "Geben Sie einen Endwert ein";
                    else if (!minOneFunctionAdded) msg += "Geben Sie mindestens eine Funktion ein";

                    Toast.makeText(TableSet.this, msg, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            //Wenn der Anwender auf die Zurück-Taste drückt, wird der Draw-Button sichtbar
            findViewById(R.id.calculate).setVisibility(View.VISIBLE);
        return super.onKeyDown(keyCode, event);
    }

    private boolean addFunction(Bundle bundle, String function, int i, int color) {
        if (!function.isEmpty()) {
            bundle.putString("function" + i, function);
            bundle.putInt("color".concat(Integer.toString(i)), color);
            return true;
        }
        return false;
    }
}