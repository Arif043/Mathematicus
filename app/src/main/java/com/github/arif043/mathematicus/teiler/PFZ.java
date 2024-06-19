package com.github.arif043.mathematicus.teiler;

import android.os.Bundle;
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

public class PFZ extends Fragment implements Keyboard.ExeListener {

    private TextView result;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pfz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final EditText input = (EditText) view.findViewById(R.id.editText);
        result = view.findViewById(R.id.textView);

        input.setInputType(0);
        input.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Keyboard keyboard = new Keyboard();
                keyboard.setTextField(input);
                keyboard.setExeListener(PFZ.this);
                keyboard.setInput(keyboard.new EditTextInput());
                FragmentUtils.bind((AppCompatActivity) getActivity(), keyboard, R.id.link3, Keyboard.TAG, true);
            }
        });
    }

    @Override
    public void onExe(String term) {
        try {
            int value = new Function(term).exe().intValue();
            //Ergebniss nicht verkürzt
            StringBuilder builder = new StringBuilder();
            int div = 2;
            while (value > 1) {
                float res;
                while ((res = (float) value / div) == Math.floor(res)) {
                    builder.append(div).append(";");
                    value = (int) res;
                }
                div++;
            }
            //Ergebniss verkürzt
            StringBuilder resultBuilder = new StringBuilder();
            String[] prim = builder.toString().split(";");
            for (int i = 0, count = 0; i <= prim.length; i++, count++) {
                if (i == prim.length || (i > 0 && !prim[i -1].equals(prim[i]))) {
                    if (count > 1)
                        resultBuilder.append(prim[i -1]).append('^').append(count).append('×');
                    else
                        resultBuilder.append(prim[i -1]).append('×');
                    count = 0;
                }
            }
            result.setText(resultBuilder.substring(0, resultBuilder.length() -1).toString());
        } catch (SyntaxException e) {
            e.printStackTrace();
        }
        FragmentUtils.remove((AppCompatActivity) getActivity(), Keyboard.TAG);
    }
}