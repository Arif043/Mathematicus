package com.github.arif043.mathematicus.einheitenrechner;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.arif043.mathematicus.R;

public class Datenrechner extends FragmentRechner {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_datenrechner, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Spinner spinnerIn = view.findViewById(R.id.dSpinnerIn),
                spinnerOut = view.findViewById(R.id.dSpinnerOut);
        final EditText input = view.findViewById(R.id.dInput),
                output = view.findViewById(R.id.dOutput);
        final TextView inputUnit = view.findViewById(R.id.dInputUnit),
                outputUnit = view.findViewById(R.id.dOutputUnit);

        inputUnit.setText(getResources().getStringArray(R.array.einheitenrechner_dateneinheitszeichen)[0]);
        outputUnit.setText(getResources().getStringArray(R.array.einheitenrechner_dateneinheitszeichen)[0]);

        // In Byte multiplizieren - Von Byte dividieren
        final double[] byteRelationship = {0.125, 0.5, 1, 125, 1000, 128, 1024, 125_000,
                        1_000_000, 131_072, 1_048_576, 125_000_000, 1_000_000_000,
                        134_217_728, 1_073_741_824}; //bis GiB

        spinnerIn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (!input.getText().toString().isEmpty()) {
                    calc(input.getText().toString(), output, byteRelationship[i], byteRelationship[spinnerOut.getSelectedItemPosition()]);
                    inputUnit.setText(getResources().getStringArray(
                            R.array.einheitenrechner_dateneinheitszeichen)[i]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerOut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (!input.getText().toString().isEmpty()) {
                    calc(input.getText().toString(), output, byteRelationship[spinnerIn.getSelectedItemPosition()], byteRelationship[i]);
                    outputUnit.setText(getResources().getStringArray(
                            R.array.einheitenrechner_dateneinheitszeichen)[i]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    calc(v.getText().toString(), output, byteRelationship[spinnerIn.getSelectedItemPosition()],
                            byteRelationship[spinnerOut.getSelectedItemPosition()]);

                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void calc(String input, EditText output, double relationIn, double relationOut) {
        double universe = Double.parseDouble(input) * relationIn;
        output.setText(getResult(universe / relationOut) + "");
    }
}