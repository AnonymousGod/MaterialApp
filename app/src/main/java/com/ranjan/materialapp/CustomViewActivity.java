package com.ranjan.materialapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ranjan.materialapp.custom_views.MyCustomView;

import androidx.appcompat.app.AppCompatActivity;

public class CustomViewActivity extends AppCompatActivity {

    MyCustomView myCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        myCustomView = findViewById(R.id.customview);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return true;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one: {
                myCustomView.customPaddingUp(30);
                break;
            }
            case R.id.two: {
                myCustomView.swapColor();
                break;
            }
            case R.id.three: {
                myCustomView.customPaddingDown(30);
                break;
            }
            case R.id.view1:
                String text = "Background";
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                break;
            case R.id.view2:
                String text2 = "Foreground";
                Toast.makeText(this, text2, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
