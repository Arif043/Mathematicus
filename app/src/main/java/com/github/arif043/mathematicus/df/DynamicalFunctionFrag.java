package com.github.arif043.mathematicus.df;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.github.arif043.mathematicus.R;
import com.github.arif043.mathematicus.keyboard.InactiveKeyboard;
import com.github.arif043.mathematicus.keyboard.Keyboard;
import com.github.arif043.mathematicus.keyboard.KeyboardAlphaState;
import ertugrul.arif.rechner.Function;
import ertugrul.arif.rechner.SyntaxException;

public class DynamicalFunctionFrag extends DialogFragment {

    public static final String TAG = "DynamicalFunctionFrag";
    private Keyboard keyboard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_dynamical_function, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText funcName = (EditText) view.findViewById(R.id.df_func_name_fld);
        final EditText term = (EditText) view.findViewById(R.id.df_term_fld);
        FragmentManager manager = getChildFragmentManager();
        keyboard = new FixedInactiveKeyboard();
        manager.beginTransaction().add(R.id.df_link, keyboard).commit();

        keyboard.setTextField(funcName);

        View.OnTouchListener listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    keyboard.setTextField((EditText) v);
                return false;
            }
        };
        funcName.setInputType(0);
        term.setInputType(0);
        funcName.setOnTouchListener(listener);
        term.setOnTouchListener(listener);

        view.findViewById(R.id.df_cancel_but).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.df_append_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!funcName.getText().toString().isEmpty() && !term.getText().toString().isEmpty()) {
                    try {
                        Function function = new Function(term.getText().toString());
                        function.exe();
                        try (ObjectOutputStream writer = new ObjectOutputStream(new BufferedOutputStream(
                                getActivity().openFileOutput("Dynamical Functions", Context.MODE_APPEND))))  {

                            writer.writeObject(function);
                            writer.flush();
                            dismiss();
                        } catch (IOException e) {
                            Log.e("GTR1", e.getMessage(), e);
                        }
                    } catch (ArithmeticException e) {
                        Toast.makeText(getContext(), "Syntaxfehler", Toast.LENGTH_LONG).show();
                    } catch (SyntaxException e) {
                        Log.e("GTR1", e.getMessage(), e);
                    }
                }
                else Toast.makeText(getContext(), "Felder dürfen nicht leer sein", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        keyboard.setState(new KeyboardAlphaState(keyboard));
    }

    public static class FixedInactiveKeyboard extends InactiveKeyboard {

        @Override
        public void initStandardButtons(View root) {
            super.initStandardButtons(root);
            Button exe = root.findViewById(R.id.exe);
            exe.setText("");
            exe.setEnabled(false);
        }
    }
}