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

public class Leangenrechner extends FragmentRechner {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leangenrechner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Spinner spinnerIn = view.findViewById(R.id.lSpinnerIn),
                spinnerOut = view.findViewById(R.id.lSpinnerOut);
        final EditText input = view.findViewById(R.id.lInput),
                 output = view.findViewById(R.id.lOutput);
        final TextView inputUnit = view.findViewById(R.id.lInputUnit),
                 outputUnit = view.findViewById(R.id.lOutputUnit);

        inputUnit.setText(getResources().getStringArray(R.array.einheitenrechner_leangeneinheitszeichen)[0]);
        outputUnit.setText(getResources().getStringArray(R.array.einheitenrechner_leangeneinheitszeichen)[0]);

        // In Meter teilen - Von Meter multiplizieren
        final double[] meterRelationship = {1000, 100, 10, 1, 0.001, 39.37007874, 3.280839895,
                                1.094091904, 0.000621504};

        spinnerIn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (!input.getText().toString().isEmpty()) {
                    calc(input.getText().toString(), output, meterRelationship[i], meterRelationship[spinnerOut.getSelectedItemPosition()]);
                    inputUnit.setText(getResources().getStringArray(
                            R.array.einheitenrechner_leangeneinheitszeichen)[i]);
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
                    calc(input.getText().toString(), output, meterRelationship[spinnerIn.getSelectedItemPosition()], meterRelationship[i]);
                    outputUnit.setText(getResources().getStringArray(
                            R.array.einheitenrechner_leangeneinheitszeichen)[i]);
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
                    calc(v.getText().toString(), output, meterRelationship[spinnerIn.getSelectedItemPosition()],
                            meterRelationship[spinnerOut.getSelectedItemPosition()]);

                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }
}