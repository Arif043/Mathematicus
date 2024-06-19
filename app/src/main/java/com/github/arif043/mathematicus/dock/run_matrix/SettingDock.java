package com.github.arif043.mathematicus.dock.run_matrix;

import android.content.Intent;
import android.view.View;

import com.github.arif043.mathematicus.RunMatrix;
import com.github.arif043.mathematicus.Settings;
import com.github.arif043.mathematicus.dock.DockItem;

public class SettingDock extends DockItem {

    public SettingDock(final RunMatrix activity) {
        super(activity, "Settings", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.post(new Intent(activity, Settings.class));
            }
        });
    }
}