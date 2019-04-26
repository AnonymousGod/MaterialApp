package com.ranjan.materialapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class OnSavedInstance extends AppCompatActivity {
    EditText editText;
    TextView textView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_saved_instance);

        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text);
        fab = findViewById(R.id.fab);

        if (savedInstanceState != null) {
            String editTextValue = savedInstanceState.getString("EditText");
            Toast.makeText(this, "Saved value received in onCreate - " + editTextValue, Toast.LENGTH_SHORT).show();
        }

        fab.setOnClickListener(view -> {
            double rand = Math.random();
            if (rand > 0.5) {
                textView.setText("One");
            } else {
                textView.setText("Two");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Toast.makeText(this, "onSaveInstanceState called", Toast.LENGTH_SHORT).show();
        outState.putString("TextView", textView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(this, "onRestoreInstanceState called", Toast.LENGTH_SHORT).show();
        String textValue = savedInstanceState.getString("TextView");
        //textView.setText(textValue);
    }

}
