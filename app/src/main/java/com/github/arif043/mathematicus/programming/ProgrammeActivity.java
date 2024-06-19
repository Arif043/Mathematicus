package com.github.arif043.mathematicus.programming;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import com.github.arif043.mathematicus.R;

public class ProgrammeActivity extends AppCompatActivity implements ProgramCreatedListener {

    private ProgramFileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programme);

        File prgDir = new File(getFilesDir(), "program");
        prgDir.mkdir();

        ListView listView = findViewById(R.id.prg_listview);
        listView.setAdapter(adapter = new ProgramFileAdapter(this));

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new ProgramDialog().show(getFragmentManager(), ProgramDialog.TAG);
            }
        });
    }

    @Override
    public void programCreated(File newPrg) {
        adapter.add(newPrg);
    }
}