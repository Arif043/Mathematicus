package com.github.arif043.mathematicus.table;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.arif043.mathematicus.R;
import ertugrul.arif.rechner.Function;
import ertugrul.arif.rechner.SyntaxException;

public class Table extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        LinearLayout root = findViewById(R.id.root);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        Function[] functions = new Function[3];

        addFunction(functions, bundle.getString("function0", ""), 0);
        addFunction(functions, bundle.getString("function1", ""), 1);
        addFunction(functions, bundle.getString("function2", ""), 2);


        showList(functions, bundle, 0, R.id.list1);
        showList(functions, bundle, 1, R.id.list2);
        showList(functions, bundle, 2, R.id.list3);

    }

    private void showList(Function[] functions, Bundle bundle, int i, int id) {
        if (functions[i] != null) {
            CustomAdapter adapter = new CustomAdapter(this, Color.BLACK);
            for  (double x = bundle.getDouble("start"); x <= bundle.getDouble("end"); x+=bundle.getDouble("delta")) {
                try {
                    adapter.add(new Point(x, functions[i].y(Double.toString(x)).doubleValue()));
                } catch (SyntaxException e) {
                    e.printStackTrace();
                }
            }
            ((ListView) findViewById(id)).setAdapter(adapter);
        }
    }

    private void addFunction(Function[] functions, String function, int i) {
        if (!function.isEmpty()) functions[i] = new Function(function);
    }
}