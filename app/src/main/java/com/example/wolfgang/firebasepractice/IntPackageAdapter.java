package com.example.wolfgang.firebasepractice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import Model.InternetPackage;


/**
 * Created by wolfgang on 6/1/2018.
 */

public class IntPackageAdapter extends RecyclerView.Adapter<IntPackageAdapter.MyViewHolder> {


    private ArrayList<InternetPackage> interList;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView validity;
        public TextView volume;
        public TextView price;
        public Button btn1;
        public Button btn2;
        public Button btn3;




        public MyViewHolder(View view)  {
            super(view);
            name = (TextView) view.findViewById(R.id.interText1);
            validity = (TextView) view.findViewById(R.id.interText3);
            volume = (TextView) view.findViewById(R.id.interText5);
            price = (TextView) view.findViewById(R.id.interText6);
            btn1 = view.findViewById(R.id.interBtn1);
            btn2 = view.findViewById(R.id.interBtn2);
            btn3 = view.findViewById(R.id.interBtn3);

            btn1.setOnClickListener(this);
            btn3.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.interBtn1:
                    Log.i("msg1","button111");
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + interList.get(getAdapterPosition()).getActivation()));
                    context.startActivity(intent);
                    break;
                case R.id.interBtn2:
                     Toast.makeText(context,"Not available for this package",Toast.LENGTH_LONG).show();
                    break;
                case R.id.interBtn3:
                    InternetPackage ip = interList.get(getAdapterPosition());
                    Gson gson = new Gson();

                    SharedPreferences sf = context.getSharedPreferences("myPreference", Context.MODE_PRIVATE);
                    String json = sf.getString("package","");
                    SharedPreferences.Editor et = sf.edit();
                    PackageList pl;
                    if(json.equals("")){
                        pl = new PackageList();
                        pl.list.add(ip);
                        json = gson.toJson(pl);
                        et.putString("package", json);
                        et.commit();
                        Log.i("msggg","heyy");
                        Toast.makeText(context,"Saved Successfully",Toast.LENGTH_LONG).show();

                    }
                    else{
                        PackageList obj = gson.fromJson(json,PackageList.class);
                        //Toast.makeText(context,obj.list.get(0).getName(),Toast.LENGTH_LONG).show();
                        Log.i("msg",obj.list.get(0).getName()+"LOOOL");

                        obj.list.add(ip);
                        json = gson.toJson(obj);
                        et.putString("package", json);
                        et.commit();
                        Toast.makeText(context,"Saved Successfully",Toast.LENGTH_LONG).show();
                    }
            }
        }
    }

    public IntPackageAdapter(ArrayList<InternetPackage> pkgList,Context c) {
        this.interList = pkgList;
        this.context = c;
    }

    @Override
    public IntPackageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.int_package_rows, parent, false);

        return new IntPackageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IntPackageAdapter.MyViewHolder holder, int position) {

        InternetPackage pkg = interList.get(position);

        holder.name.setText(pkg.getName());
        holder.validity.setText(pkg.getValidity());
        holder.volume.setText(pkg.getVolume());
        holder.price.setText(pkg.getPrice());


    }

    @Override
    public int getItemCount() {
        return interList.size();
    }
}


