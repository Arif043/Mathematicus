package com.github.arif043.mathematicus.dock.graph;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.arif043.mathematicus.R;
import com.github.arif043.mathematicus.dock.DockItem;

public class VarX extends DockItem {

    public VarX(final AppCompatActivity activity) {
        super(activity, "X", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentUtils.bind(activity, new Fragment(), R.id.graph_link, TAG);
            }
        });
    }

    public static class Fragment extends android.app.Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_varx, container, false);
            return root;
        }
    }
}