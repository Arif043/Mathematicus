package com.github.arif043.mathematicus.dock;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.github.arif043.mathematicus.R;

public abstract class DockItem {

    public static final String TAG = "DockItemFragment";
    private Button button;

    public DockItem(Context context, String buttonName, View.OnClickListener listener) {
        button = new Button(context);
        button.setText(buttonName);
        button.setOnClickListener(listener);
        button.setBackgroundColor(context.getResources().getColor(R.color.background));
        button.setTextColor(context.getResources().getColor(android.R.color.holo_blue_light));
    }

    public Button getButton() {
        return button;
    }
}