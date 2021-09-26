package com.example.mymath;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public static final String DETERMINANT = "Determinant";
    public static final String INVERSE = "Inverse";
    public static final String MULTIPLY = "Multiply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ListView listView = (ListView) findViewById(R.id.listview);

        AdapterView.OnItemClickListener click = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, NInput.class);
                        intent.putExtra(NInput.TYPE, DETERMINANT);
//                        try {
                            startActivity(intent);
//                        } catch (Exception e) {
//                            System.out.println(e);
//                        }
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, NInput.class);
                        intent1.putExtra(NInput.TYPE, INVERSE);
//                        try {
                            startActivity(intent1);
//                        } catch (Exception e) {
//                            System.out.println(e);
//                        }
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, InputMultySize.class);
                        startActivity(intent2);
                        break;
                    default:
                        break;
                }
            }
        };

        listView.setOnItemClickListener(click);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.exit:
                finish();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
