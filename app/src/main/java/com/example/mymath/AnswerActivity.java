package com.example.mymath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import Math.MyMath;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        getSupportActionBar().setTitle(R.string.answer);
        draw();
    }

    public void draw() {
        TextView first = findViewById(R.id.firstmatrix);
        TextView second = findViewById(R.id.secondmatrix);
        TextView answer = findViewById(R.id.answermatrix);
        int[][] x = FirstActivity.matrix;
        int[][] y = SecondActivity.matrix;

        String s1 = "";

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                s1+=x[i][j];
                if(j!=0 || j!=x.length-1)
                    s1+=" ";
            }
            s1+='\n';
        }

//        s1=String.format("%3s",s1);

        String s2 = "";
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y[i].length; j++) {
                if (j == 0)
                    s2 += y[i][j];
                else
                    s2 += " "+y[i][j];
            }
            s2 += '\n';
        }

        String s3 = MyMath.MultipleOf(x, y);

        first.setText(s1);
        second.setText(s2);
        answer.setText(s3);
    }

}
