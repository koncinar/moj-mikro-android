package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;

public class MyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText nameInput = (EditText) findViewById(R.id.name_input);
                Editable name = nameInput.getText();
                TextView messageView = (TextView) findViewById(R.id.message);
                String message = getString(R.string.hello_text);
                messageView.setText(String.format(message, name));
            }
        });

        ArrayAdapter<CharSequence> regionsAdapter =
                ArrayAdapter.createFromResource(this, R.array.all_regions, android.R.layout.simple_spinner_item);
        regionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.regions_spinner)).setAdapter(regionsAdapter);

        final String[] all_experiences = getResources().getStringArray(R.array.all_android_experiences);
        SeekBar experiencesSeekBar = (SeekBar) findViewById(R.id.android_experiences_seek_bar);
        experiencesSeekBar.setMax(all_experiences.length - 1);
        experiencesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((TextView) findViewById(R.id.android_experiences_display)).setText(all_experiences[i]);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}


