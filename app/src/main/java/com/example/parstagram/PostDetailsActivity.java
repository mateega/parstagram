package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

public class PostDetailsActivity extends AppCompatActivity {

    private TextView tvUsername;
    private ImageView ivPostImage;
    private TextView tvDescription;
    private TextView tvTimeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvUsername = findViewById(R.id.tvUsername);
        tvDescription = findViewById(R.id.tvDescription);
        ivPostImage = findViewById(R.id.ivPostImage);
        tvTimeStamp = findViewById(R.id.tvTimeStamp);

        tvUsername.setText(getIntent().getStringExtra("username"));
        tvDescription.setText(getIntent().getStringExtra("description"));

        String imageUrl = getIntent().getStringExtra("image");

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(getApplicationContext()).load(imageUrl).into(ivPostImage);
        }


        tvTimeStamp.setText(getIntent().getStringExtra("timeStamp"));
    }
}