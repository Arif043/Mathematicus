package com.github.arif043.mathematicus.keyboard;

import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentTransaction;

import com.github.arif043.mathematicus.R;

/*
    Vergessen sie nicht, dass sie die Id der
    auszublendenden Button als Bundle übergeben müssen
 */
public class InactiveKeyboard extends Keyboard {

    public InactiveKeyboard() {
        setInput(new EditTextInput());
    }

    @Override
    public void initStandardButtons(final View root) {
        super.initStandardButtons(root);
        ((Button) root.findViewById(R.id.exe)).setText("OK");
        root.findViewById(R.id.exe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.remove(InactiveKeyboard.this).commit();
                if (getArguments().getInt("id", -1) != -1)
                    getActivity().findViewById(getArguments().getInt("id")).setVisibility(View.VISIBLE);
            }
        });
    }
}