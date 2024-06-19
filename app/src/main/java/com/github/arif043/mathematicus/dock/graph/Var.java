package com.github.arif043.mathematicus.dock.graph;

import android.view.View;

import com.github.arif043.mathematicus.R;
import com.github.arif043.mathematicus.dock.Dock;
import com.github.arif043.mathematicus.dock.DockItem;
import com.github.arif043.mathematicus.graph.GraphViewer;

public class Var extends DockItem {

    public Var(final GraphViewer activity) {
        super(activity, "Var", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setDock(new Dock(activity.getDock(), new VarY(activity), new VarX(activity)), R.id.linear);
            }
        });
    }
}