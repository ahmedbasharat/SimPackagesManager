package com.example.wolfgang.firebasepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private TextView tv;
    private TextView tv2;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        iv = (ImageView) findViewById(R.id.imageView);
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        tv.startAnimation(myAnim);
        tv2.startAnimation(myAnim);
        iv.startAnimation(myAnim);
        final Intent i = new Intent(this,Home1.class);
        Thread timer = new Thread()
        {
            public void run()
            {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
