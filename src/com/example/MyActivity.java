package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText nameInput = (EditText) findViewById(R.id.name_input);
                Editable name = nameInput.getText();
                TextView messageView = (TextView) findViewById(R.id.message);
                String message = getString(R.string.hello_text);
                messageView.setText(String.format(message, name));
            }
        });
    }
}


