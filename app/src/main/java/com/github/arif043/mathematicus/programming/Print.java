package com.github.arif043.mathematicus.programming;

public class Print implements Expression {

    private Value val;

    public Print(Value val) {
        this.val = val;
    }

    @Override
    public Object interprate(Context context) {
        //sysout
        return null;
    }
}