package com.github.arif043.mathematicus.programming;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.github.arif043.mathematicus.R;

public class ProgramFileAdapter extends BaseAdapter {

    private List<File> prgFiles;
    private LayoutInflater inflater;

    public ProgramFileAdapter(android.content.Context context) {
        inflater = LayoutInflater.from(context);
        File prgDir = new File(context.getFilesDir(), "program");
        prgFiles = new LinkedList<>(Arrays.asList(prgDir.listFiles()));
    }

    @Override
    public int getCount() {
        return prgFiles.size();
    }

    @Override
    public Object getItem(int i) {
        return prgFiles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void add(File file) {
        prgFiles.add(file);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.program_info, viewGroup, false);
            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.name);
            view.setTag(holder);
        }
        else holder = (ViewHolder) view.getTag();

        File f = (File) getItem(i);
        holder.name.setText("\t" + f.getName() + "\t\t" + f.length() + " Bytes");
        return view;
    }

    static class ViewHolder {

        TextView name;

    }
}