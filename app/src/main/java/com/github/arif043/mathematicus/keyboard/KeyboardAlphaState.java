package com.github.arif043.mathematicus.keyboard;

import static com.github.arif043.mathematicus.keyboard.Key.*;

import android.view.View;

import com.github.arif043.mathematicus.R;

/*
    Der Alpha-Zustand. Der Alpha-Zustand wird durch das Drücken des Alpha-Buttons aktiviert.
    Wenn der Anwender Variablen definieren und Buchstaben eingeben will, ist diese
    Klasse dafür verantwortlich
 */
public class KeyboardAlphaState extends KeyboardState {

    public KeyboardAlphaState(Keyboard board) {
        super(board);
    }

    @Override
    //Hier werden die Buttons aktualisiert
    public void changeButtons(View root) {
        setControlButton(root, R.id.alpha, R.color.background, R.color.alpha, new KeyboardNormalState(getBoard()));

        setButton(root, R.id.var_x, "A", "A", NULL);
        setButton(root, R.id.log, "B", "B", NULL);
        setButton(root, R.id.ln, "C", "C", NULL);
        setButton(root, R.id.sin, "D", "D", NULL);
        setButton(root, R.id.cos, "E", "E", NULL);
        setButton(root, R.id.tan, "F", "F", NULL);
        setButton(root, R.id.klammer_auf, "G", "G", NULL);
        setButton(root, R.id.klammer_zu, "H", "H", NULL);
        setButton(root, R.id.komma, "I", "I", NULL);
        setButton(root, R.id._7, "J", "J", NULL);
        setButton(root, R.id._8, "K", "K", NULL);
        setButton(root, R.id._9, "L", "L", NULL);
        setButton(root, R.id._4, "M", "M", NULL);
        setButton(root, R.id._5, "N", "N", NULL);
        setButton(root, R.id._6, "O", "O", NULL);
        setButton(root, R.id.mul, "P", "P", NULL);
        setButton(root, R.id.div, "Q", "Q", NULL);
        setButton(root, R.id._1, "R", "R", NULL);
        setButton(root, R.id._2, "S", "S", NULL);
        setButton(root, R.id._3, "T", "T", NULL);
        setButton(root, R.id.add, "U", "U", NULL);
        setButton(root, R.id.sub, "X", "X", NULL);
        setButton(root, R.id._0, "Y", "Y", NULL);
        setButton(root, R.id.dot, "Z", "Z", NULL);
        setButton(root, R.id._10_faktor, "SPACE", " ", NULL);
    }
}