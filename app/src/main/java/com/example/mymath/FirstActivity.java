package com.example.mymath;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class FirstActivity extends AppCompatActivity {

    int row, column, startMargin, margin, height, width;
    static int[][] matrix;

    ConstraintLayout layout;
    ConstraintSet set;

    DisplayMetrics displayMetrics;

    EditText[][] matrixText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        row=getIntent().getIntExtra(InputMultySize.FIRST_ROW_SIZE,2);
        column=getIntent().getIntExtra(InputMultySize.FIRST_COLUMN_SIZE,2);

        getSupportActionBar().setTitle(R.string.first);

        matrixText = new EditText[row][column];
        matrix = new int[row][column];
        layout = new ConstraintLayout(this);

        layout.setId(View.generateViewId());
        set = new ConstraintSet();

        startMargin = 50;
        margin = 5;
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = (displayMetrics.widthPixels - margin * (column - 1) - 2 * startMargin) / column;
        height = width;

        draw();

    }

    public void draw(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrixText[i][j] = new EditText(this);
                matrixText[i][j].setId(View.generateViewId());
                matrixText[i][j].setInputType(InputType.TYPE_CLASS_NUMBER);
                matrixText[i][j].setHint("0");
                matrixText[i][j].setGravity(Gravity.CENTER);
                matrixText[i][j].setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
                layout.addView(matrixText[i][j]);

                set.constrainWidth(matrixText[i][j].getId(), width);
                set.constrainHeight(matrixText[i][j].getId(), height);
                set.connect(matrixText[i][j].getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, startMargin + (margin + width) * j);
                set.connect(matrixText[i][j].getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, startMargin + (margin + height) * i);
                set.applyTo(layout);
            }
        }

        button = new Button(this);

        button.setId(View.generateViewId());
        button.setText(R.string.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        matrix[i][j] = 0;
                        try {
                            matrix[i][j] = Integer.parseInt(matrixText[i][j].getText().toString());
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
                intent.putExtra(InputMultySize.SECOND_ROW_SIZE,getIntent().getIntExtra(InputMultySize.SECOND_ROW_SIZE,2));
                intent.putExtra(InputMultySize.SECOND_COLUMN_SIZE,getIntent().getIntExtra(InputMultySize.SECOND_COLUMN_SIZE,2));
                startActivity(intent);
            }
        });
        layout.addView(button);

        set.constrainWidth(button.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainHeight(button.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(button.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(button.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(button.getId(), ConstraintSet.TOP, matrixText[row - 1][column - 1].getId(), ConstraintSet.BOTTOM, 10);

        set.applyTo(layout);

        setContentView(layout);
    }
}
