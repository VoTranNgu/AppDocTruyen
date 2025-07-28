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

public class SchoollifeActivity extends AppCompatActivity implements UserGridAdapter2.UserCallback{

    Button btBackC;
    TextView tvHocDuongC;
    RecyclerView rvHocDuongC;
    ArrayList<User> lstUser;
    UserGridAdapter2 userAdapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoollife);
        btBackC=findViewById(R.id.btBack);
        tvHocDuongC=findViewById(R.id.tvHocDuong);
        //
        rvHocDuongC=findViewById(R.id.rvHocDuong);
        LoadData();
        userAdapter3=new UserGridAdapter2(lstUser,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rvHocDuongC.setAdapter(userAdapter3);
        rvHocDuongC.setLayoutManager(gridLayoutManager);
        //
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
        lstUser.add(new User(6,"Oshi no ko","6.jpg"));
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