package com.github.arif043.mathematicus.einheitenrechner;

import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentRechner extends Fragment {

    protected void calc(String input, EditText output, double relationIn, double relationOut) {
        double universe = Double.parseDouble(input) / relationIn;
        output.setText(getResult(universe * relationOut) + "");
    }

    protected Number getResult(double result) {
        if (result == Math.floor(result)) return (long) result;
        return result;
    }
}