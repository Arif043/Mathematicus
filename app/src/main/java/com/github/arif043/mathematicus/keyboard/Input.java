package com.github.arif043.mathematicus.keyboard;

//Adapter für Eingabeelemente von von Keyboard
public interface Input {

    void append(String toAppend);
    int length();
    void setText(String text);
    void clear();
    String getText();
}