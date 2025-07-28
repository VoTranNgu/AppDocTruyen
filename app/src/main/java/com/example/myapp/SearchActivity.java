package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import TruyenTranh.TruyenTranh;

public class SearchActivity extends AppCompatActivity {
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh>truyenTranhArrayList;

    GridView dsTruyen;
    Button mbtnaction;
    GridView DSTruyen;
    EditText edt_TimKiem;
    Button mbtncomedy;
    Button mbtnhorror;
    Button mbtnromance;
    Button mbtnschoollife;
    Button mbtndrama;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_search);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int selectedItemId = item.getItemId();

            if (selectedItemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (selectedItemId == R.id.bottom_search) {
                return true;
            } else if (selectedItemId == R.id.bottom_settings) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (selectedItemId == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }

            return false;
        });

        anhXa();
        init();
        setUp();
        setClick();
        mbtnschoollife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Layout 2
                Intent intent = new Intent(SearchActivity.this, SchoollifeActivity.class);
                startActivity(intent);
            }
        });
        mbtnaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, ActionActivity.class);
                startActivity(intent);
            }
        });
        mbtnhorror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, HorrorActivity.class);
                startActivity(intent);
            }
        });
        mbtndrama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, DramaActivity.class);
                startActivity(intent);
            }
        });
        mbtncomedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, ComedyActivity.class);
                startActivity(intent);
            }
        });
        mbtnromance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, RomanceActivity.class);
                startActivity(intent);
            }
        });
    }
    ArrayList<User> lstUser;
    UserAdapter userAdapter;
    RecyclerView rvListC;

    private void anhXa()
    {
        mbtnaction=(Button) findViewById(R.id.btn_action);
        mbtncomedy=(Button) findViewById(R.id.btn_comedy);
        mbtnhorror=(Button) findViewById(R.id.btn_Horror);
        mbtnromance=(Button) findViewById(R.id.btn_romance);
        mbtndrama=(Button) findViewById(R.id.btn_drama);
        mbtnschoollife=(Button) findViewById(R.id.btn_schoollife);
        edt_TimKiem=(EditText)findViewById(R.id.edt_TimKiem);
        dsTruyen=(GridView)findViewById(R.id.DSTruyen);
    }
    private void init(){
        truyenTranhArrayList=new ArrayList<>();
        truyenTranhArrayList.add(new TruyenTranh("Tokyo Ghoul",R.drawable.tokyo));
        truyenTranhArrayList.add(new TruyenTranh("Solo Leveling",R.drawable.solo));
        truyenTranhArrayList.add(new TruyenTranh("Peter Killer",R.drawable.spy));
        truyenTranhArrayList.add(new TruyenTranh("Mashle",R.drawable.mash));
        truyenTranhArrayList.add(new TruyenTranh("ReLife",R.drawable.relife));
        truyenTranhArrayList.add(new TruyenTranh("Record Of Ragnarok",R.drawable.record));
        adapter=new TruyenTranhAdapter(this,0,truyenTranhArrayList);
    };
    private void setUp(){
        dsTruyen.setAdapter(adapter);
    }
    private void setClick(){
        edt_TimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s=edt_TimKiem.getText().toString();
                adapter.sortTruyen(s);
            }
        });

    }


    }

