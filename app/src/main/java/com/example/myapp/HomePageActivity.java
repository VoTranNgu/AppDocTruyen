package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity implements UserGridAdapter2.UserCallback{
    RecyclerView rvListC;
    ArrayList<User> lstUser;
    UserGridAdapter2 userAdapter;

    //FloatingActionButton fbAdd;
    RecyclerView rvListCompleteC;
    UserGridAdapter2 userAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        rvListC=findViewById(R.id.rvList);
        LoadData();
        userAdapter= new UserGridAdapter2(lstUser,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rvListC.setAdapter(userAdapter);
        rvListC.setLayoutManager(gridLayoutManager);

        /*fbAdd = findViewById(R.id.fbAdd);
        fbAdd.setOnClickListener(view -> addUserDialog());
        lstUser = UserDataQuery.getAll(this);
        userAdapter= new UserAdapter(lstUser,this);
        userAdapter.setCallback(this);
        //
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListC.setAdapter(userAdapter);
        rvListC.setLayoutManager(gridLayoutManager);*/

        //
        rvListCompleteC=findViewById(R.id.rvListComplete);
        LoadData3();
        userAdapter1 = new UserGridAdapter2(lstUser, this);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this,3);
        rvListCompleteC.setAdapter(userAdapter1);
        rvListCompleteC.setLayoutManager(gridLayoutManager1);
        //

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int selectedItemId = item.getItemId();

            if (selectedItemId == R.id.bottom_home) {
                return true;
            } else if (selectedItemId == R.id.bottom_search) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
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

        ImageSlider imageSlider=findViewById(R.id.slider1);
        ArrayList<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.pr1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.pr2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.pr3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
    }
    /*void addUserDialog() {
        //Khởi tạo dialog để thêm người dùng
        AlertDialog.Builder altertDialog = new AlertDialog.Builder(HomePageActivity.this);
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
                Toast.makeText(HomePageActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {
                User us = new User(0, name, avatar);
                long id = UserDataQuery.insert(HomePageActivity.this,us);
                if(id > 0){
                    Toast.makeText(HomePageActivity.this, "Thêm truyện thành công ", Toast.LENGTH_LONG).show();
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
    }*/

    void LoadData()
    {
        lstUser=new ArrayList<>();
        lstUser.add(new User(1,"Solo Leveling","1.jpg"));
        lstUser.add(new User(2,"Doraemon","2.jpg"));
        lstUser.add(new User(3,"Shuumatsu no Valkyrie","3.jpg"));
        lstUser.add(new User(4,"Horobi no kuni no Seifukusha","4.jpg"));
        lstUser.add(new User(5,"Sousou no Frieren","5.jpg"));
        lstUser.add(new User(6,"Oshi no ko","6.jpg"));
    }
    void LoadData3()
    {
        lstUser=new ArrayList<>();
        lstUser.add(new User(5,"Sousou no Frieren","5.jpg"));
        lstUser.add(new User(6,"Oshi no ko","6.jpg"));
        lstUser.add(new User(7,"ReLife","7.jpg"));
        lstUser.add(new User(8,"Peter Killer","8.jpg"));
        lstUser.add(new User(9,"Mashle","9.jpg"));
        lstUser.add(new User(10,"Tokyo Ghoul","10.jpg"));
    }

    void resetData(){
        lstUser.clear();
        lstUser.addAll(UserDataQuery.getAll(HomePageActivity.this));
        userAdapter.notifyDataSetChanged();
    }
    void updateUserDialog(User us) {
        // khởi tạo dialog để cập nhật người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomePageActivity.this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
        alertDialog.setView(dialogView);
        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        EditText edAvatar =(EditText) dialogView.findViewById(R.id.edAvatar);
        // gán dữ liệu
        edName.setText(us.getName());
        edAvatar.setText(us.getAvatar());
        //
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            us.setName(edName.getText().toString());
            us.setAvatar(edAvatar.getText().toString());
            if (us.name.isEmpty()) {
                Toast.makeText(HomePageActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {

                int id = UserDataQuery.update(HomePageActivity.this, us);
                if (id > 0) {
                    Toast.makeText(HomePageActivity.this, "Cập nhật truyện thành công", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Hủy", (dialog, which)->{
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();
    }

    @Override
    public void onItemClick(String name) {
        Intent i =new Intent(this,DetailActivity.class);
        i.putExtra("userName",name);
        startActivity(i);
    }
}
