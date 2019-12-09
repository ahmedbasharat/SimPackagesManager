package com.example.wolfgang.firebasepractice;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

import Model.InternetPackage;
import Util.Constants;



public class favActivity extends AppCompatActivity {


    private ArrayList<InternetPackage> stList = new ArrayList<>();
    private RecyclerView recyclerView;
    private IntPackageAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);


        recyclerView = findViewById(R.id.recycler_view2);

        sAdapter = new IntPackageAdapter(stList, this);


        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(sAdapter);


        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                InternetPackage st = stList.get(position);
                Toast.makeText(getApplicationContext(), st.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        Gson gson = new Gson();

        SharedPreferences sf = getSharedPreferences("myPreference", Context.MODE_PRIVATE);

        String json = sf.getString("package", "");
        if (json != "") {
            PackageList obj = gson.fromJson(json, PackageList.class);
            for (int i = 0; i < obj.list.size(); i++) {
                stList.add(obj.list.get(i));
            }

            sAdapter.notifyDataSetChanged();


        }

    }
}