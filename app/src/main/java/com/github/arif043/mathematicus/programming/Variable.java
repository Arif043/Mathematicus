package com.github.arif043.mathematicus.programming;

public class Variable implements Expression {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Object interprate(Context context) {
        return context.getVariables().get(name);
    }
}