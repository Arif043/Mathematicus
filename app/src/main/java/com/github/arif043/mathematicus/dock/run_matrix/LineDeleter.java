package com.github.arif043.mathematicus.dock.run_matrix;

import android.view.View;

import com.github.arif043.mathematicus.RunMatrix;
import com.github.arif043.mathematicus.dock.DockItem;

public class LineDeleter extends DockItem {

    public LineDeleter(final RunMatrix activity) {
        super(activity, "Delete Line", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] splitted = activity.getEdit().getText().toString().split("\n");
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < splitted.length -2; i++)
                    builder.append(splitted[i]).append("\n");
                activity.getEdit().setText(builder.toString());
            }
        });
    }
}