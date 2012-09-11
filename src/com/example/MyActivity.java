package com.example;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveData();
            }
        });
        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                loadData();
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
        loadData();
    }

    private void saveData() {
        SharedPreferences.Editor editor = getSharedPreferences("mma_preferences", 0).edit();
        String name = ((TextView) findViewById(R.id.name_input)).getText().toString();
        editor.putString("name", name);
        int gender = ((RadioGroup) findViewById(R.id.gender_radio_group)).getCheckedRadioButtonId();
        editor.putInt("gender", gender);
        int region = ((Spinner) findViewById(R.id.regions_spinner)).getSelectedItemPosition();
        editor.putInt("region", region);
        boolean aliens = ((CheckBox) findViewById(R.id.aliens_checkbox)).isChecked();
        editor.putBoolean("aliens", aliens);
        int experiences = ((SeekBar) findViewById(R.id.android_experiences_seek_bar)).getProgress();
        editor.putInt("experiences", experiences);
        editor.commit();
        Toast.makeText(this, "Podatki so shranjeni", Toast.LENGTH_SHORT).show();
    }

    private void loadData() {
        SharedPreferences pref = getSharedPreferences("mma_preferences", 0);
        String name = pref.getString("name", "");
        ((TextView) findViewById(R.id.name_input)).setText(name);
        int gender = pref.getInt("gender", -1);
        if (gender != -1) {
            ((RadioButton) findViewById(gender)).setChecked(true);
        }
        int region = pref.getInt("region", 0);
        ((Spinner) findViewById(R.id.regions_spinner)).setSelection(region);
        boolean aliens = pref.getBoolean("aliens", false);
        ((CheckBox) findViewById(R.id.aliens_checkbox)).setChecked(aliens);
        int experiences = pref.getInt("experiences", 0);
        ((SeekBar) findViewById(R.id.android_experiences_seek_bar)).setProgress(experiences);
    }
}


