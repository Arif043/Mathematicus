package com.github.arif043.mathematicus.keyboard;

import static com.github.arif043.mathematicus.keyboard.Key.*;

import android.view.View;
import android.widget.Button;

import com.github.arif043.mathematicus.R;

public class KeyboardShiftState extends KeyboardState {

    public KeyboardShiftState(Keyboard board) {
        super(board);
    }

    @Override
    public void changeButtons(View root) {
        setControlButton(root, R.id.shift, R.color.background, R.color.shift, new KeyboardNormalState(getBoard()));

        setButton(root, R.id.xh2, "√", "√", NULL);
        setButton(root, R.id.log, "10^x", "10^", NULL);
        setButton(root, R.id.ln, "e^x", "e^", NULL);
        setButton(root, R.id.sin, "asin", "asin(", NULL);
        setButton(root, R.id.cos, "acos", "acos(", NULL);
        setButton(root, R.id.tan, "atan", "atan(", NULL);
        setButton(root, R.id.klammer_zu, "x-¹", "^-1", NULL);
        setButton(root, R.id.neg, "Ans", "Ans", NULL);
    }

    @Override
    protected void setButton(View root, int id, String text, final String toAppend, final Key key) {
        ((Button) root.findViewById(id)).setText(text);
        root.findViewById(id).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getBoard().setState(new KeyboardNormalState(getBoard()));
                if (getBoard().getKeyListener() != null) getBoard().getKeyListener().onKey(key);
                getBoard().getInput().append(toAppend);
            }
        });
    }
}