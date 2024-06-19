package com.github.arif043.mathematicus.programming;

import java.util.HashMap;

public class Context {

    private HashMap<String, Object> variables = new HashMap<>();

    public void addVar(String name, Object value) {
        variables.put(name, value);
    }

    public HashMap<String, Object> getVariables() {
        return variables;
    }
}