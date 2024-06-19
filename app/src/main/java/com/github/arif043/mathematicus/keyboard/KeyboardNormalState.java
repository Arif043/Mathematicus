package com.github.arif043.mathematicus.keyboard;

import static com.github.arif043.mathematicus.keyboard.Key.*;

import android.view.View;

import com.github.arif043.mathematicus.R;

import ertugrul.arif.rechner.Evaluator;

// Der Normalzustand. Wie ein gewöhnlicher Taschenrechner
public class KeyboardNormalState extends KeyboardState {

    public KeyboardNormalState(Keyboard board) {
        super(board);
    }

    @Override
    //Hier werden die Buttons aktualisiert
    public void changeButtons(final View root) {
        setControlButton(root, R.id.shift, R.color.shift, R.color.background, new KeyboardShiftState(getBoard()));
        setControlButton(root, R.id.alpha, R.color.alpha, R.color.background, new KeyboardAlphaState(getBoard()));
        setButton(root, R.id.exp, "^", "^", EXP);
        setButton(root, R.id.xh2, "x²", "^2", EXP2);
        setButton(root, R.id.var_x, "x0,t", "x", NULL);
        setButton(root, R.id.log, "log", "log(", NULL);
        setButton(root, R.id.ln, "ln", "ln(", NULL);
        setButton(root, R.id.sin, "sin", "sin(", NULL);
        setButton(root, R.id.cos, "cos", "cos(", NULL);
        setButton(root, R.id.tan, "tan", "tan(", NULL);
        setButton(root, R.id.klammer_auf, "(", "(", NULL);
        setButton(root, R.id.klammer_zu, ")", ")", NULL);
        setButton(root, R.id.komma, ",", ",", NULL);
        setButton(root, R.id._0, "0", "0", NULL);
        setButton(root, R.id._1, "1", "1", NULL);
        setButton(root, R.id._2, "2", "2", NULL);
        setButton(root, R.id._3, "3", "3", NULL);
        setButton(root, R.id._4, "4", "4", NULL);
        setButton(root, R.id._5, "5", "5", NULL);
        setButton(root, R.id._6, "6", "6", NULL);
        setButton(root, R.id._7, "7", "7", NULL);
        setButton(root, R.id._8, "8", "8", NULL);
        setButton(root, R.id._9, "9", "9", NULL);
        setButton(root, R.id.dot, ".", ".", NULL);
        setButton(root, R.id.mul, "×", "×", Key.MUL);
        setButton(root, R.id.add, "+", "+", Key.ADD);
        setButton(root, R.id.sub, "-", "-", Key.SUB);
        setButton(root, R.id.div, ":", ":", Key.DIV);
        setButton(root, R.id.neg, "(-)", "(" + Evaluator.NEG, Key.NEG);

        setButton(root, R.id.referrers, "←", "←", NULL);
    }
}