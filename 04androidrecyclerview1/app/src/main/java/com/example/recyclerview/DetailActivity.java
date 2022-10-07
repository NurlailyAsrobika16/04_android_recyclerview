package com.example.recyclerview;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imageView;
        imageView = findViewById(R.id.cat_image);
        TextView textView = findViewById(R.id.cat_details);
        //Gets the data that is sent to it from the Main Activity and displays it
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            textView.setText(getIntent().getStringExtra("details"));
            Glide.with(this).load(getIntent().getIntExtra("image", 0)).into(imageView);
        }
    }
}
