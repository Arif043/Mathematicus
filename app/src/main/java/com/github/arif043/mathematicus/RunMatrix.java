package com.github.arif043.mathematicus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.arif043.mathematicus.dock.Dock;

import com.github.arif043.mathematicus.dock.run_matrix.Lines;
import com.github.arif043.mathematicus.dock.run_matrix.SettingDock;
import com.github.arif043.mathematicus.keyboard.Input;
import com.github.arif043.mathematicus.keyboard.Key;
import com.github.arif043.mathematicus.keyboard.Keyboard;

import ertugrul.arif.rechner.Executeable;
import ertugrul.arif.rechner.ExpressionStatement;
import ertugrul.arif.rechner.Interpreter;
import ertugrul.arif.rechner.SyntaxException;

//Diese Klasse simuliert einen Taschenrechner
public class RunMatrix extends App implements Keyboard.ExeListener, Keyboard.OnKeyListener {

    private static final String KEY = "Rechnungen";
    private EditText edit;
    private Input input;
    private boolean insertAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_matrix);
//        getSupportActionBar().hide();
//
        input = new StringInput();

        edit = findViewById(R.id.editText);
        edit.setKeyListener(null);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        edit.setText(preferences.getString(KEY, ""));

        initDocks();

        //Bindet ein Keyboard-Objekt ein
        Keyboard keyboard = Keyboard.newInstance(edit, this, this, input);
        FragmentUtils.bind(this, keyboard, R.id.ui, Keyboard.TAG, false);
    }

    @Override
    protected void onStop() {
        super.onStop();
        edit.setText(edit.getText().subSequence(0, edit.getText().length() - input.length()));
        getPreferences(MODE_PRIVATE).edit().putString(KEY, edit.getText().toString()).apply();
    }

    @Override
    public void initDocks() {
        LinearLayout dockLayout = (LinearLayout) findViewById(R.id.link1);
        Dock root = new Dock(null, new Lines(this), new SettingDock(this));
        setDock(root, R.id.link1);
        findViewById(R.id.root_matrix).invalidate();
    }

    @Override
    public void onExe(String input) {
        Executeable e = Interpreter.getExecuteable(input);
        try {
            insertAns = true;
            edit.append("\n" + e.exe().toString() + "\n");
            if (!ExpressionStatement.isExpressionStatement(input))
                new ExpressionStatement("Ans" + ExpressionStatement.getReferrers() + e.exe().toString()).exe();
        } catch (SyntaxException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onKey(Key e) {
        if (insertAns && e.isOperator())
            input.append("Ans");
        insertAns = false;
    }

    public EditText getEdit() {
        return edit;
    }

    private class StringInput implements Input {

        private StringBuilder buffer = new StringBuilder();

        @Override
        public void append(String toAppend) {
            edit.append(toAppend);
            buffer.append(toAppend);
        }

        public int length() {
            return buffer.length();
        }

        //wird nur zum Löschen aufgerufen
        public void setText(String text) {
            int toRemoveChars = buffer.length() - text.length();
            edit.setText(edit.getText().subSequence(0, edit.length() -toRemoveChars));

            buffer.delete(buffer.length() -toRemoveChars, buffer.length());
        }

        public void clear() {
            buffer.delete(0, buffer.length());
        }

        public String getText() {
            return buffer.toString();
        }
    }
}