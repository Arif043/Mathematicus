package com.github.arif043.mathematicus.graph;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.github.arif043.mathematicus.dock.graph.Var;

import com.github.arif043.mathematicus.App;
import com.github.arif043.mathematicus.R;
import com.github.arif043.mathematicus.dock.Dock;
import ertugrul.arif.rechner.Function;

//Diese Klasse beinhaltet das Koordinatensystem
public class GraphViewer extends App implements View.OnTouchListener {

    public static final int PROGRESS = 2, VAR_Y = 3;
    private Koordinatensystem ks;
    private float touchX = Float.MAX_VALUE, touchY = Float.MAX_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialog(PROGRESS);
        setContentView(R.layout.activity_graph);
        ks = (Koordinatensystem) findViewById(R.id.koordinatensystem);
        ks.setOnTouchListener(this);
        ks.setFunctions((Function[]) getIntent().getExtras().get("functions"));
        getSupportActionBar().hide();
        initDocks();
    }

    public void initDocks() {
        final LinearLayout dockLayout = (LinearLayout) findViewById(R.id.linear);
        Dock root  = new Dock(null, new Var(this));
        setDock(root, R.id.linear);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && getDock().getPredecessor() != getDock()) {
            setDock(getDock().getPredecessor(), R.id.linear);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (touchX != Float.MAX_VALUE) {
            ks.setDeltaPosition(event.getX(0) - touchX,event.getY(0) - touchY);
            ks.invalidate();
        }
        touchX = event.getX(0);
        touchY = event.getY(0);

        if (event.getAction() == KeyEvent.ACTION_UP)
            touchX = touchY = Float.MAX_VALUE;
        return true;
    }

    public Koordinatensystem getKs() {
        return ks;
    }
}