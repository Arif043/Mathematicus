package com.github.arif043.mathematicus.keyboard;

public enum Key {

    // Für undefinierte Buttons
    NULL,

    A,
    ADD(false, true),
    SUB(false, true),
    MUL(false, true),
    DIV(false, true),
    EXP(false, true),
    EXP2(false, true),
    NEG(false, true);

    private boolean isControlButton, isOperator;

    Key(boolean isControlButton, boolean isOperator) {
        this.isControlButton = isControlButton;
        this.isOperator = isOperator;
    }
    //Für Buchstaben
    Key() {
        this(false, false);
    }

    public boolean isOperator() {
        return isOperator;
    }
}