package com.example.wolfgang.firebasepractice;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import Util.Constants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

import Model.InternetPackage;


/**
 * Created by wolfgang on 6/1/2018.
 */

public class InternetFragment extends Fragment {

    //private  RecyclerView rec;
    private ArrayList<InternetPackage> stList = new ArrayList<>();
    private RecyclerView recyclerView;
    private IntPackageAdapter sAdapter;
    private DatabaseReference ref;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // View view = inflater.inflate(R.layout.student_list, container, false);
        //inflater.inflate(R.layout.student_list,container);
        View view = inflater.inflate(R.layout.int_package_list,container,false);


        recyclerView = view.findViewById(R.id.recycler_view1);

        sAdapter = new IntPackageAdapter(stList,this.getActivity());


        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(sAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                InternetPackage st = stList.get(position);
                Toast.makeText(getActivity().getApplicationContext(), st.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        //new GetServerData().execute();
        //prepareSampleMovieData();
        Bundle b = this.getArguments();
        String key = b.getString("key");
        switch (key){
            case Constants.telenorInternet:
                ref = FirebaseDatabase.getInstance().getReference("telenorInternet");
                break;

            case Constants.telenorCall:

                ref = FirebaseDatabase.getInstance().getReference("telenorCall");
                break;
            case Constants.telenorSim:

                ref = FirebaseDatabase.getInstance().getReference("telenorSms");
                break;

            case Constants.zongInternet:
                ref = FirebaseDatabase.getInstance().getReference("zongInternet");
                break;

            case Constants.zongCall:
                ref = FirebaseDatabase.getInstance().getReference("zongCall");
                break;

            case Constants.zongSms:
                ref = FirebaseDatabase.getInstance().getReference("zongSms");
                break;

            case Constants.ufoneCall:
                ref = FirebaseDatabase.getInstance().getReference("ufoneCall");
                break;
            case Constants.ufoneInternet:
                ref = FirebaseDatabase.getInstance().getReference("ufoneInternet");
                break;
            case Constants.ufoneSms:
                ref = FirebaseDatabase.getInstance().getReference("ufoneSms");
                break;
            case Constants.mobInternet:
                ref = FirebaseDatabase.getInstance().getReference("jazzInternet");
                break;
            case Constants.mobCall:
                ref = FirebaseDatabase.getInstance().getReference("jazzCall");
                break;

            case Constants.mobSms:
                ref = FirebaseDatabase.getInstance().getReference("jazzSms");
                break;
        }


        /*ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Student newPost = dataSnapshot.getValue(Student.class);
                stList.add(newPost);
                Log.i("msg",newPost.getStudentName());
                sAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });*/

        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                stList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    InternetPackage menu = postSnapshot.getValue(InternetPackage.class);
                    Log.i("msg",menu.getName());
                    Log.i("msg",menu.getPrice());
                    stList.add(menu);
                }
                sAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        return view;

    }
}



