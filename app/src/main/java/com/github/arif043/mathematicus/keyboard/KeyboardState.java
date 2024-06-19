package com.github.arif043.mathematicus.keyboard;

import android.view.View;
import android.widget.Button;

// Die Superklasse der einzelnen Zuständen
public abstract class KeyboardState {

    // Eine Referenz auf das Keyboard-Objekt, damit Unterklassen selbständig die Zustände ändern
    // können.
    private Keyboard board;

    public KeyboardState(Keyboard board) {
        this.board = board;
    }

    public abstract void changeButtons(View root);

    // Methode zur Änderung der Buttons, um doppelten Code zu vermeiden
    protected void setButton(View root, int id, String text, final String toAppend, final Key key) {
        ((Button) root.findViewById(id)).setText(text);
        root.findViewById(id).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (board.getKeyListener() != null) board.getKeyListener().onKey(key);
                board.getInput().append(toAppend);
            }
        });
    }
    // Methode zur Änderung der Steuer-Buttons, um doppelten Code zu vermeiden
    protected void setControlButton(View root, int id, int textColor, int backColor, final KeyboardState state) {
        ((Button)root.findViewById(id)).setTextColor(root.getResources().getColor(textColor));
        root.findViewById(id).setBackgroundColor(root.getResources().getColor(backColor));
        root.findViewById(id).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                board.setState(state);
            }
        });
    }

    public Keyboard getBoard() {
        return board;
    }
}