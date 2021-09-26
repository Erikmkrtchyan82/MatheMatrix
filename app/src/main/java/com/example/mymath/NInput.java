package com.example.mymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NInput extends AppCompatActivity {

    public static final String TYPE = "type";
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_input);
        type = getIntent().getStringExtra(TYPE);
    }

    public void buttonClick(View view) {
        EditText editText = findViewById(R.id.edittextn);
        int n = 0;
        try {
            n = Integer.parseInt(editText.getText().toString());
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        if (n >= 9 || n < 2) {
            TextView textView = findViewById(R.id.textview);
            textView.setText(R.string.error);
        } else {
//        if (n <= 8 && n > 1) {
            Intent intent = new Intent(NInput.this, DetActivity.class);
            intent.putExtra(DetActivity.SIZE, n);
            intent.putExtra(TYPE, type);
            startActivity(intent);
        }
    }
}
