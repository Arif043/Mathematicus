package com.github.arif043.mathematicus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import com.github.arif043.mathematicus.df.DynamicalFunctionsActivity;
import com.github.arif043.mathematicus.einheitenrechner.Einheitenrechner;
import com.github.arif043.mathematicus.graph.GraphSet;
import com.github.arif043.mathematicus.numsys.NumberSystemActivity;
import com.github.arif043.mathematicus.programming.ProgrammeActivity;
import com.github.arif043.mathematicus.table.TableSet;
import com.github.arif043.mathematicus.teiler.TeilerActivity;
import ertugrul.arif.rechner.ExpressionStatement;
import ertugrul.arif.rechner.FileLocate;

// Die Main-Klasse
public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File dir = new File(getFilesDir(), "var");
        if (!dir.exists()) dir.mkdir();
        FileLocate.setVarDir(dir);

        ExpressionStatement.setReferrers("←");

        setContentView(R.layout.activity_menu);

        findViewById(R.id.matrix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, RunMatrix.class));
            }
        });
        findViewById(R.id.graph_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, GraphSet.class));
            }
        });
        findViewById(R.id.einheitenrechner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Einheitenrechner.class));
            }
        });
        findViewById(R.id.table).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, TableSet.class));
            }
        });
        findViewById(R.id.teiler).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, TeilerActivity.class));
            }
        });
        findViewById(R.id.dynamischeFunktionen).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, DynamicalFunctionsActivity.class));
            }
        });
        findViewById(R.id.programming).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, ProgrammeActivity.class));
            }
        });
        findViewById(R.id.numbersystem).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, NumberSystemActivity.class));
            }
        });
    }
}