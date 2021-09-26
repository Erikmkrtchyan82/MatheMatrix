package com.example.mymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputMultySize extends AppCompatActivity {

    int n1, n2, m1, m2;
    public static final String FIRST_ROW_SIZE = "n1", FIRST_COLUMN_SIZE = "m1", SECOND_ROW_SIZE = "n2", SECOND_COLUMN_SIZE = "m2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_multy_size);
        getSupportActionBar().setTitle("Multiply");
    }

    public void onClick(View view) {
        n1 = n2 = m1 = m2 = 0;
        EditText fn = findViewById(R.id.firstrow);
        EditText fm = findViewById(R.id.firstcol);
        EditText sn = findViewById(R.id.secondrow);
        EditText sm = findViewById(R.id.secondcol);

        TextView textView = findViewById(R.id.errortitle);

        try {
            n1 = Integer.parseInt(fn.getText().toString());
            m1 = Integer.parseInt(fm.getText().toString());
            n2 = Integer.parseInt(sn.getText().toString());
            m2 = Integer.parseInt(sm.getText().toString());
        } catch (Exception e) {
            textView.setText(R.string.inputsize);
        }

        if (m1 != n2) {
            textView.setText(R.string.errortitle);
        } else {
            if (n1 > 5 || n1 < 2 || n2 > 5 || n2 < 2) {
                textView.setText(R.string.rowsizeerror);
            } else {
                if ( m2 > 8 || m2 < 2) {
                    textView.setText(R.string.colsizeerror);
                } else {
                    Intent intent = new Intent(this, FirstActivity.class);
                    intent.putExtra(FIRST_ROW_SIZE, n1);
                    intent.putExtra(FIRST_COLUMN_SIZE, m1);
                    intent.putExtra(SECOND_ROW_SIZE, n2);
                    intent.putExtra(SECOND_COLUMN_SIZE, m2);
                    startActivity(intent);
                }
            }
        }

    }

}
