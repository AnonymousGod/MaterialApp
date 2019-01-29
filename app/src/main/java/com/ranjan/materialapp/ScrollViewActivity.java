package com.ranjan.materialapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import androidx.appcompat.app.AppCompatActivity;

public class ScrollViewActivity extends AppCompatActivity {
    String TAG = "ScrollViewActivity";
    Button button;
    View view1, view2, view3;
    ScrollView scrollView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        scrollView = findViewById(R.id.scroll_view);
        button = findViewById(R.id.button1);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        editText = findViewById(R.id.edit_text);

        KeyboardVisibilityEvent.setEventListener(this, isOpen -> {
            if (isOpen) {
                scrollTo();
            } else {
                logThings();
            }
        });
    }


    private void logThings() {
        Log.d(TAG, "view1 height " + view1.getHeight() + " width " + view1.getWidth()
                + " top " + view1.getTop() + " bottom " + view1.getBottom());

        Log.d(TAG, "view2 height " + view2.getHeight() + " width " + view2.getWidth()
                + " top " + view2.getTop() + " bottom " + view2.getBottom());

        Log.d(TAG, "view3 height " + view3.getHeight() + " width " + view3.getWidth()
                + " top " + view3.getTop() + " bottom " + view3.getBottom());

        Log.d(TAG, "button height " + button.getHeight() + " width " + button.getWidth()
                + " top " + button.getTop() + " bottom " + button.getBottom());
    }

    private void scrollTo() {
        try {
            logThings();
            scrollView.post(() -> scrollView.scrollTo(0, editText.getTop()));
        } catch (Exception e) {
            Log.d(TAG, "exception" + e.getLocalizedMessage());
        }
    }
}
