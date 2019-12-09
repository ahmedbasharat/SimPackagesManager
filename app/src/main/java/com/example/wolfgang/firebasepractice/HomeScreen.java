package com.example.wolfgang.firebasepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        btn1 = findViewById(R.id.t1);
        btn2 = findViewById(R.id.t2);
        btn3 = findViewById(R.id.t3);
        btn4 = findViewById(R.id.t4);
        btn5 = findViewById(R.id.t5);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.t5:
                Intent i = new Intent(this,Main2Activity.class);
                startActivity(i);


        }
    }
}
