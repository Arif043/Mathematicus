package com.github.arif043.mathematicus.teiler;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.github.arif043.mathematicus.FragmentUtils;
import com.github.arif043.mathematicus.R;
import com.github.arif043.mathematicus.keyboard.Keyboard;
import ertugrul.arif.rechner.Function;
import ertugrul.arif.rechner.SyntaxException;

public class Teiler extends Fragment implements Keyboard.ExeListener {

    private TextView result;
    private EditText input;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teiler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        result = view.findViewById(R.id.textView);
        input = (EditText) view.findViewById(R.id.editText);

        input.setInputType(0);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                result.setVisibility(View.INVISIBLE);

                //Neues Fragment (Keyboard) hinzufügen
                Keyboard keyboard = new Keyboard();
                keyboard.setTextField(input);
                keyboard.setInput(keyboard.new EditTextInput());
                keyboard.setExeListener(Teiler.this);
                FragmentUtils.bind((AppCompatActivity) getActivity(), keyboard, R.id.link, Keyboard.TAG, true);
            }
        });

    }

    @Override
    public void onExe(String term) {
        result.setText("");
        int value = 0;
        try {
            value = new Function(input.getText().toString()).exe().intValue();
        } catch (SyntaxException e) {
            Log.e("Teiler", e.getMessage());
        }
        //int value = Integer.parseInt(v.getText().toString());
        for (int i = 1; i <= value; i++) {
            double div = (double) value / i;
            if (div == Math.floor(div)) {
                result.append((int) div + "\n");
            }
        }
        FragmentUtils.remove((AppCompatActivity) getActivity(), Keyboard.TAG);
        result.setVisibility(View.VISIBLE);
    }
}