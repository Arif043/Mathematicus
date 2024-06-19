package com.github.arif043.mathematicus.df;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.arif043.mathematicus.R;

public class DynamicalFunctionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamical_functions);

        findViewById(R.id.addDFBut).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DynamicalFunctionFrag().show(getSupportFragmentManager(), DynamicalFunctionFrag.TAG);
            }
        });

    }
}