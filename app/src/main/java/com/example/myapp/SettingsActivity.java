package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity implements UserAdapter.UserCallback {
    ArrayList<User> lstUser;
    UserAdapter userAdapter;
    FloatingActionButton fbAdd;
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        rvList=findViewById(R.id.Truyen);
        fbAdd = findViewById(R.id.fbAdd);
        fbAdd.setOnClickListener(view -> addUserDialog());
        lstUser = UserDataQuery.getAll(this);
        userAdapter= new UserAdapter(lstUser,this);
        userAdapter.setCallback(this);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,3);
        rvList.setAdapter(userAdapter);
        rvList.setLayoutManager(linearLayoutManager);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_settings);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int selectedItemId = item.getItemId();

            if (selectedItemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (selectedItemId == R.id.bottom_search) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (selectedItemId == R.id.bottom_settings) {

                return true;
            } else if (selectedItemId == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }

            return false;
        });
    }
    void addUserDialog() {
        //Khởi tạo dialog để thêm người dùng
        AlertDialog.Builder altertDialog = new AlertDialog.Builder(SettingsActivity.this);
        altertDialog.setTitle("Thêm mới ");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
        altertDialog.setView(dialogView);
        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edAvatar);
        altertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            String name = edName.getText().toString();
            String avatar = edAvatar.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(SettingsActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {
                User us = new User(0, name, avatar);
                long id = UserDataQuery.insert(SettingsActivity.this,us);
                if(id > 0){
                    Toast.makeText(SettingsActivity.this, "Thêm truyện thành công ", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        altertDialog.setNegativeButton("Huỷ", (dialog, which) ->{
            dialog.dismiss();
        });
        altertDialog.create();
        altertDialog.show();
    }

    void resetData(){
        lstUser.clear();
        lstUser.addAll(UserDataQuery.getAll(SettingsActivity.this));
        userAdapter.notifyDataSetChanged();
    }

    void updateUserDialog (User us){
        // khởi tạo dialog để cập nhật người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingsActivity.this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
        alertDialog.setView(dialogView);
        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edAvatar);
        // gán dữ liệu
        edName.setText(us.getName());
        edAvatar.setText(us.getAvatar());
        //
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            us.setName(edName.getText().toString());
            us.setAvatar(edAvatar.getText().toString());
            if (us.name.isEmpty()) {
                Toast.makeText(SettingsActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {

                int id = UserDataQuery.update(SettingsActivity.this, us);
                if (id > 0) {
                    Toast.makeText(SettingsActivity.this, "Cập nhật truyện thành công", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Hủy", (dialog, which) -> {
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();
    }

    @Override
    public void onItemClick(String id) {
        Intent i =new Intent(this,DetailActivity.class);
        i.putExtra("userName",id);
        startActivity(i);
    }

    @Override
    public void onItemDeleteClicked(User us, int position) {
        boolean rs = UserDataQuery.delete(SettingsActivity.this, us.id);
        if (rs) {
            Toast.makeText(this, " Xoá Thành công", Toast.LENGTH_LONG).show();
            resetData();
        } else {
            //  Toast.makeText(this, " Xoá thất bại", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemEditClicked(User us, int position) { updateUserDialog(us); }

}

