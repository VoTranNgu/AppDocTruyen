package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RomanceActivity extends AppCompatActivity implements UserGridAdapter2.UserCallback{
    Button btBackC;
    TextView tvromance;
    RecyclerView rvromance;
    ArrayList<User> lstUser;
    UserGridAdapter2 userAdapter8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romance);
        tvromance=findViewById(R.id.tvromance);
        //
        rvromance=findViewById(R.id.rvromance);
        LoadData();
        userAdapter8=new UserGridAdapter2(lstUser,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rvromance.setAdapter(userAdapter8);
        rvromance.setLayoutManager(gridLayoutManager);
        //
        btBackC=findViewById(R.id.btBack);
        btBackC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    void LoadData()
    {
        lstUser=new ArrayList<>();
        lstUser.add(new User(7,"ReLife","7.jpg"));
        lstUser.add(new User(5,"Sousou no Frieren","5.jpg"));
        lstUser.add(new User(4,"Horobi no kuni no Seifukusha","4.jpg"));
    }

    @Override
    public void onItemClick(String name) {
        Intent i =new Intent(this,DetailActivity.class);
        i.putExtra("userName",name);
        startActivity(i);
    }
}