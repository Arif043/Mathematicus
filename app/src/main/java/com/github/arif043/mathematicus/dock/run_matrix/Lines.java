package com.github.arif043.mathematicus.dock.run_matrix;

import android.view.View;

import com.github.arif043.mathematicus.R;
import com.github.arif043.mathematicus.RunMatrix;
import com.github.arif043.mathematicus.dock.Dock;
import com.github.arif043.mathematicus.dock.DockItem;

public class Lines extends DockItem {

    public Lines(final RunMatrix activity) {
        super(activity, "Lines", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setDock(new Dock(activity.getDock(), new LineDeleter(activity), new AllLinesDeleter(activity)), R.id.link1);
            }
        });
    }
}