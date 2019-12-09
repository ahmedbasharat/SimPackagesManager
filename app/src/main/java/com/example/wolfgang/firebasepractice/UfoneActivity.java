package com.example.wolfgang.firebasepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Util.Constants;

public class UfoneActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufone);

        btn1 = findViewById(R.id.telBtn1);
        btn2 = findViewById(R.id.telBtn2);
        btn3 = findViewById(R.id.telBtn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FragActivity.class);
                i.putExtra("key", Constants.ufoneInternet);
                startActivity(i);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FragActivity.class);
                i.putExtra("key", Constants.ufoneCall);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FragActivity.class);
                i.putExtra("key", Constants.ufoneSms);
                startActivity(i);
            }
        });
    }
}

