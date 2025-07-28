package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    ImageView imgTruyenC;
    Button btBackC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgTruyenC = findViewById(R.id.imgTruyen);
        btBackC=findViewById(R.id.btBack);
        String name = getIntent().getStringExtra("userName");

        try {
            // Đọc ảnh từ assets
            InputStream ims = getAssets().open("images/a" + name);
            // Tạo Drawable từ InputStream
            Drawable d = Drawable.createFromStream(ims, null);
            // Thiết lập ảnh vào ImageView
            imgTruyenC.setImageDrawable(d);
            ims.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }


        btBackC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}