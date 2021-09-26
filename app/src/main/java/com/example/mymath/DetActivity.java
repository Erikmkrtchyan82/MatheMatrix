package com.example.mymath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.AttributedCharacterIterator;

import Math.MyMath;

public class DetActivity extends AppCompatActivity {

    public static final String SIZE = "size";
    int size, startMargin, margin, height, width;
    int[][] matrix;

    String type;

    ConstraintLayout layout;
    ConstraintSet set;

    DisplayMetrics displayMetrics;

    EditText[][] matrixText;
    TextView answer, answerText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        size = (Integer) getIntent().getExtras().get(SIZE);
        type = getIntent().getStringExtra(NInput.TYPE);

        getSupportActionBar().setTitle(type);

        matrixText = new EditText[size][size];
        matrix = new int[size][size];
        layout = new ConstraintLayout(this);

        layout.setId(View.generateViewId());
        set = new ConstraintSet();

        startMargin = 50;
        margin = 5;
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = (displayMetrics.widthPixels - margin * (size - 1) - 2 * startMargin) / size;
        height = width;

        draw();
    }

    public void draw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrixText[i][j] = new EditText(this);
                matrixText[i][j].setId(View.generateViewId());
                matrixText[i][j].setInputType(InputType.TYPE_CLASS_NUMBER);
                matrixText[i][j].setHint("0");
                matrixText[i][j].setGravity(Gravity.CENTER);
                layout.addView(matrixText[i][j]);

                set.constrainWidth(matrixText[i][j].getId(), width);
                set.constrainHeight(matrixText[i][j].getId(), height);
                set.connect(matrixText[i][j].getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, startMargin + (margin + width) * j);
                set.connect(matrixText[i][j].getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, startMargin + (margin + height) * i);
                set.applyTo(layout);
            }
        }

        answer = new TextView(this);
        answerText = new TextView(this);
        button = new Button(this);

        button.setId(View.generateViewId());
        button.setText(R.string.solve);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        matrix[i][j] = 0;
                        try {
                            matrix[i][j] = Integer.parseInt(matrixText[i][j].getText().toString());
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
                if (type.equalsIgnoreCase(MainActivity.DETERMINANT)) {
                    answerText.setText("Determinant is: ");
                    answer.setText(MyMath.DeterminantOf(matrix));
                } else if (type.equalsIgnoreCase(MainActivity.INVERSE)) {
                    answerText.setText("Inversed matrix is: ");
                    answer.setText(MyMath.InverseOf(matrix));
                }
            }
        });
        layout.addView(button);

        answer.setId(View.generateViewId());
        layout.addView(answer);

        answerText.setId(View.generateViewId());
        layout.addView(answerText);

        set.constrainWidth(button.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainHeight(button.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(button.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(button.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(button.getId(), ConstraintSet.TOP, matrixText[size - 1][size - 1].getId(), ConstraintSet.BOTTOM, 10);

        set.constrainWidth(answerText.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainHeight(answerText.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(answerText.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(answerText.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(answerText.getId(), ConstraintSet.TOP, button.getId(), ConstraintSet.BOTTOM, 3);
        set.setHorizontalBias(answerText.getId(), 0.15f);

        set.constrainWidth(answer.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainHeight(answer.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(answer.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 10);
        set.connect(answer.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 10);
        if (type.equalsIgnoreCase(MainActivity.DETERMINANT)) {
            set.connect(answer.getId(),ConstraintSet.TOP,button.getId(),ConstraintSet.BOTTOM,3);
        } else {
            set.connect(answer.getId(), ConstraintSet.TOP, answerText.getId(), ConstraintSet.BOTTOM, 3);
        }
        set.setHorizontalBias(answer.getId(), 0.75f);

        set.applyTo(layout);

        setContentView(layout);
    }
}

