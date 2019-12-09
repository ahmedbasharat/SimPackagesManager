package com.example.wolfgang.firebasepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Util.Constants;

public class Main2Activity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn1 = findViewById(R.id.telBtn1);
        btn2 = findViewById(R.id.telBtn2);
        btn3 = findViewById(R.id.telBtn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FragActivity.class);
                i.putExtra("key", Constants.telenorInternet);
                startActivity(i);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FragActivity.class);
                i.putExtra("key", Constants.telenorCall);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FragActivity.class);
                i.putExtra("key", Constants.telenorSim);
                startActivity(i);
            }
        });
    }
}
