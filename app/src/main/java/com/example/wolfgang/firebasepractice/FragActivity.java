package com.example.wolfgang.firebasepractice;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Util.Constants;

public class FragActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        /*FragmentTest fragment = new FragmentTest();
        fragmentTransaction.replace(R.id.fram,fragment);
        fragmentTransaction.commit(); // save the changes*/
        String key = getIntent().getExtras().getString("key");
        InternetFragment fragment = new InternetFragment();
        Bundle b = new Bundle();
        b.putString("key", key);
        fragment.setArguments(b);
        fragmentTransaction.replace(R.id.fram,fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
