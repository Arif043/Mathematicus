package com.github.arif043.mathematicus.programming;

public class Value implements Expression {

    private Object value;

    public Value(Object value) {
        this.value = value;
    }

    @Override
    public Object interprate(Context context) {
        return value.toString();
    }
}