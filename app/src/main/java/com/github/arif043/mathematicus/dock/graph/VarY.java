package com.github.arif043.mathematicus.dock.graph;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.arif043.mathematicus.graph.GraphViewer;

import com.github.arif043.mathematicus.FragmentUtils;
import com.github.arif043.mathematicus.R;
import com.github.arif043.mathematicus.dock.DockItem;
import ertugrul.arif.rechner.SyntaxException;

public class VarY extends DockItem {

    private static GraphViewer activity;

    public VarY(final GraphViewer activity) {
        super(activity, "Y", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.rebind(activity, new Fragment(), R.id.graph_link, TAG, false);
            }
        });
        VarY.activity = activity;
    }

    public static class Fragment extends androidx.fragment.app.Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_vary, container, false);

            //Textview y
            int[] colors = getResources().getIntArray(R.array.functions_color);
            final TextView y = (TextView) root.findViewById(R.id.y);
            y.setTextColor(colors[0]);

            //Edittext x
            ((EditText) root.findViewById(R.id.x1)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        try {
                            StringBuilder builder = new StringBuilder("y = ");
                            y.setText(builder.append(activity.getKs().getFunctions()[0].y(v.getText().toString())).toString());
                            InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                            return true;
                        } catch (SyntaxException e) {
                            //In GraphSet wird auf Syntaxfehler geprüft
                        }
                    }
                    return false;
                }
            });
            return root;
        }
    }
}