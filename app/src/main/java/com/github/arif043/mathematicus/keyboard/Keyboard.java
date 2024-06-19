package com.github.arif043.mathematicus.keyboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.github.arif043.mathematicus.R;

// Diese Klasse erweitert die Fragment-Klasse, weil das Keyboard wiederverwendet werden kann.
//x ist im Rechnermodus immer 0
public class Keyboard extends Fragment {

    public static final String TAG = "Keyboard";
    //Tastaturen haben mehrere Zustände (Normal, Alpha, Shift)
    private KeyboardState state;
    //Textfeld das die Berechnungen anzeigt
    private EditText textField;
    private Input input;
    private ExeListener exeListener;
    private OnKeyListener keyListener;

    public static Keyboard newInstance(EditText textField, ExeListener exeListener, OnKeyListener keyListener, Input input) {
        Keyboard newKeyboard = new Keyboard();
        newKeyboard.setTextField(textField);
        newKeyboard.setExeListener(exeListener);
        newKeyboard.setKeyListener(keyListener);
        newKeyboard.setInput(input);
        return newKeyboard;
    }

    public static Keyboard newInstance(EditText textField, ExeListener exeListener, Input input) {
        return newInstance(textField, exeListener, null, input);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Jedes Keyboard-Objekt fängt mit den Normalzustand an
        state = new KeyboardNormalState(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_keyboard, container, false);
        // Ändert sein Zustand
        state.changeButtons(root);
        // Hier werden die Standartbuttons initialisiert. Meistens sind es Steuerbuttons
        initStandardButtons(root);
        return root;
    }

    public void initStandardButtons(View root) {
        root.findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (input.length() > 0) {
                    String text = input.getText();
                    input.setText(text.substring(0, text.length() - 1));
                }
            }
        });
        root.findViewById(R.id.del).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                input.setText("");
                return true;
            }
        });
        root.findViewById(R.id.exe).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!input.getText().isEmpty()) {
                    exeListener.onExe(input.getText());
                    input.clear();
                }
            }
        });
    }

    public EditText getTextField() {
        return textField;
    }

    public void setTextField(EditText textField) {
        this.textField = textField;
    }

    public void setExeListener(ExeListener listener) {
        this.exeListener = listener;
    }

    public void setKeyListener(OnKeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public OnKeyListener getKeyListener() {
        return keyListener;
    }

    public void setState(KeyboardState state) {
        this.state = state;
        state.changeButtons(getView());
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public interface ExeListener {
        void onExe(String term);
    }

    public interface OnKeyListener {
        void onKey(Key e);
    }

    public class EditTextInput implements Input {

        @Override
        public void append(String appendingText) {
            textField.append(appendingText);
        }

        @Override
        public int length() {
            return textField.getText().toString().length();
        }

        @Override
        public void setText(String text) {
            textField.setText(text);
        }

        @Override
        public String getText() {
            return textField.getText().toString();
        }

        @Override
        public void clear() {

        }
    }
}