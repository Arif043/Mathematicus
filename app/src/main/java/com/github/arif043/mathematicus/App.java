package com.github.arif043.mathematicus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.github.arif043.mathematicus.dock.Dock;

// Die Superklasse für Apps.
// App bezeichnet die Anwendungen (momentan nur Run Matrix, Table und GraphViewer)
public abstract class App extends AppCompatActivity {

    public static final int SYNTAX_ERROR_MESSAGE = 0;

    //Jede App besitzt ein Dock (Taskleiste)
    private Dock dock;

    public void initDocks(){}

    public void setDock(Dock dock, int layoutID) {
        this.dock = dock;
        dock.show((LinearLayout) findViewById(layoutID));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        if (id == SYNTAX_ERROR_MESSAGE) {
            dialog = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog).setMessage("Syntax Error").create();
        }
        return dialog;
    }

    public void post(Intent intent) {
        startActivity(intent);
    }

    public Dock getDock() {
        return dock;
    }
}