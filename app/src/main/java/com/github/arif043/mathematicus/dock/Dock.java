package com.github.arif043.mathematicus.dock;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

// Klasse für die Taskleiste der Apps.
// Diese Klasse ist nicht vollständig und nicht einsatzbereit
public class Dock {

    private DockItem[] items;

    //Vorgänger
    private Dock predecessor;

    public  Dock(Dock predecessor, DockItem... items) {
        this.items = items;
        this.predecessor = predecessor;
    }

    public void show(LinearLayout layout) {
        layout.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 80, 0);
        for (DockItem item : items) {
            item.getButton().setLayoutParams(params);
            layout.addView(item.getButton());
        }
    }

    public static Button createButton(Context context, String text) {
        Button b = new Button(context);
        b.setText(text);
        return b;
    }

    public Dock getPredecessor() {
        return predecessor != null ? predecessor : this;
    }

    public void setPredecessor(Dock predecessor) {
        this.predecessor = predecessor;
    }
}