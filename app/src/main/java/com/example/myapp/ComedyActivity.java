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

public class ComedyActivity extends AppCompatActivity implements UserGridAdapter2.UserCallback{
    Button btBackC;
    TextView tvcomedy;
    RecyclerView rvcomedy;
    ArrayList<User> lstUser;
    UserGridAdapter2 userAdapter7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comedy);
        tvcomedy=findViewById(R.id.tvcomedy);
        //
        rvcomedy=findViewById(R.id.rvcomedy);
        LoadData();
        userAdapter7=new UserGridAdapter2(lstUser,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rvcomedy.setAdapter(userAdapter7);
        rvcomedy.setLayoutManager(gridLayoutManager);
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
        lstUser.add(new User(2,"Doraemon","2.jpg"));
        lstUser.add(new User(7,"ReLife","7.jpg"));
        lstUser.add(new User(9,"Mashle","9.jpg"));
    }

    @Override
    public void onItemClick(String name) {
        Intent i =new Intent(this,DetailActivity.class);
        i.putExtra("userName",name);
        startActivity(i);
    }
}