package com.github.arif043.mathematicus.programming;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;

import com.github.arif043.mathematicus.R;

public class ProgramDialog extends DialogFragment {

    public static final String TAG = ProgramDialog.class.getSimpleName();
    private ProgramCreatedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_programdialog, container);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ProgramCreatedListener) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText name = view.findViewById(R.id.prg_dia_name);
        view.findViewById(R.id.prg_dia_save).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!name.getText().toString().isEmpty()) {
                    File prgDir = new File(getActivity().getFilesDir(), "program");
                    File newPrg = new File(prgDir, name.getText().toString());
                    try {
                        newPrg.createNewFile();
                        dismiss();
                        listener.programCreated(newPrg);
                    } catch (IOException e) {
                        Log.e("GTR1", e.getMessage(), e);
                    }
                }
                else Toast.makeText(getActivity(), "Sie müssen einen Namen eingeben", Toast.LENGTH_SHORT).show();
            }
        });
    }
}