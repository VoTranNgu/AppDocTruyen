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

public class DramaActivity extends AppCompatActivity implements UserGridAdapter2.UserCallback {
    Button btBackC;
    TextView tvdramaC;
    RecyclerView rvdramaC;
    ArrayList<User> lstUser;
    UserGridAdapter2 userAdapter6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drama);
        tvdramaC=findViewById(R.id.tvdrama);
        //
        rvdramaC=findViewById(R.id.rvdrama);
        LoadData();
        userAdapter6=new UserGridAdapter2(lstUser,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rvdramaC.setAdapter(userAdapter6);
        rvdramaC.setLayoutManager(gridLayoutManager);
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
        lstUser.add(new User(8,"Peter Killer","8.jpg"));
        lstUser.add(new User(10,"Tokyo Ghoul","10.jpg"));
        lstUser.add(new User(1,"Solo Leveling","1.jpg"));
        lstUser.add(new User(3,"Shuumatsu no Valkyrie","3.jpg"));
    }

    @Override
    public void onItemClick(String name) {
        Intent i =new Intent(this,DetailActivity.class);
        i.putExtra("userName",name);
        startActivity(i);
    }
}