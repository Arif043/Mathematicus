package com.github.arif043.mathematicus.table;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.arif043.mathematicus.R;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    private static Context context;
    private ArrayList<Point> points;
    private int color;

    public CustomAdapter(Context context, int color) {
        CustomAdapter.context = context;
        points = new ArrayList<>(20);
        this.color = color;
    }

    public void add(Point point) {
        points.add(point);
    }

    @Override
    public int getCount() {
        return points.size();
    }

    @Override
    public Object getItem(int position) {
        return points.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.table_product, null);
        TextView x = v.findViewById(R.id.x);
        TextView y = v.findViewById(R.id.y);
        x.setText(Double.toString(points.get(i).getX()));
        x.setTextColor(color);
        y.setText(Double.toString(points.get(i).getY()));
        y.setTextColor(color);
        return v;
    }
}