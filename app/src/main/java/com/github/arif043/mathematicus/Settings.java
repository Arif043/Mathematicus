package com.github.arif043.mathematicus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final CheckBox bigValues = findViewById(R.id.big_values);
        final CheckBox trennZeichen = findViewById(R.id.trennzeichen);
        final RadioButton unendlichKomma = findViewById(R.id.unendlich_komma);
        RadioButton begrenztKomma = findViewById(R.id.begrenzt_komma);
        final EditText editKomma = findViewById(R.id.edit_komma);

        unendlichKomma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editKomma.setEnabled(false);
            }
        });
        begrenztKomma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editKomma.setEnabled(true);
            }
        });

        final SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(this);
        bigValues.setChecked(pre.getBoolean("bigValues", false));
        trennZeichen.setChecked(pre.getBoolean("trennZeichen", false));
        if (pre.getBoolean("unendlichKomma", false)) {
            unendlichKomma.toggle();
            editKomma.setEnabled(false);
        }
        else begrenztKomma.toggle();
        editKomma.setText(pre.getString("kommaStellen", ""));

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pre.edit();
                editor.putBoolean("bigValues", bigValues.isChecked());
                editor.putBoolean("trennZeichen", trennZeichen.isChecked());
                editor.putBoolean("unendlichKomma", unendlichKomma.isChecked());
                editor.putString("kommaStellen", editKomma.getText().toString());
                editor.apply();
            }
        });
    }
}