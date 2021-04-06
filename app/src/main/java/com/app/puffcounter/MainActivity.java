package com.app.puffcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "puffs";
    FloatingActionButton single;
    FloatingActionButton ten;

    TextView tv ;
    TextView greet ;
    TextView total ;
    SharedPreferences settings;
    List<String> welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        single = findViewById(R.id.one);
        ten = findViewById(R.id.ten);
        greet = findViewById(R.id.greet);
        tv = findViewById(R.id.count);
        total = findViewById(R.id.total);
       settings = getSharedPreferences(PREFS_NAME, 0);

            total.setText(String.valueOf(settings.getInt("ct",0)));
            welcome=new ArrayList();
        welcome.add("Rock On!");welcome.add("Have a good one!");welcome.add("Great weather!");welcome.add("Beunos Dias!");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Random a = new Random();
        int r = a.nextInt(4);
        greet.setText(welcome.get(r));
        single.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int a = Integer.parseInt(String.valueOf(tv.getText()));
                int b = Integer.parseInt(String.valueOf(total.getText()));
                a++;
                String store = String.valueOf(a);
                String store2 = String.valueOf(++b);
                tv.setText(store);
                total.setText(store2);

                settings.edit().putInt("ct", b).commit();
            }



        });
        ten.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int a = Integer.parseInt(String.valueOf(tv.getText()));
                int b = Integer.parseInt(String.valueOf(total.getText()));
                a+=10;b+=10;
                String store = String.valueOf(a);
                String store2 = String.valueOf(b);
                tv.setText(store);
                total.setText(store2);

                settings.edit().putInt("ct", b).commit();
            }



        });

        single.setOnLongClickListener(new View.OnLongClickListener(){


            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(getApplication(), "cleared", Toast.LENGTH_SHORT).show();
                tv.setText("0");
                return true;
            }
        });


        total.setOnLongClickListener(new View.OnLongClickListener(){


            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplication(), "cleared", Toast.LENGTH_SHORT).show();
                total.setText("0");
                return true;
            }
        });

    }
}