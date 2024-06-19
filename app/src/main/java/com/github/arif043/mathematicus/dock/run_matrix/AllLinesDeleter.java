package com.github.arif043.mathematicus.dock.run_matrix;

import android.view.View;

import com.github.arif043.mathematicus.RunMatrix;
import com.github.arif043.mathematicus.dock.DockItem;

public class AllLinesDeleter extends DockItem {

    public AllLinesDeleter(final RunMatrix activity) {
        super(activity, "Delete All Lines", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getEdit().setText("");
            }
        });
    }
}