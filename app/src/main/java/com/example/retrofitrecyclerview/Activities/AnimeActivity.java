package com.example.retrofitrecyclerview.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.retrofitrecyclerview.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class AnimeActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout = null;
    TextView names, teams,descriptions;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        bindIds();
        loadParsedData();
    }

    private void bindIds() {
        names = findViewById(R.id.aa_anime_name);
        teams = findViewById(R.id.aa_studio);
        descriptions = findViewById(R.id.aa_description);
        img = findViewById(R.id.aa_thumbnail);
        collapsingToolbarLayout =  findViewById(R.id.collaspeToolbarId);
    }

    private void loadParsedData() {
        // Recieve data
        String name = getIntent().getExtras().getString("name");
        String description = getIntent().getExtras().getString("description");
        String studio = getIntent().getExtras().getString("team");
        String image_url = getIntent().getExtras().getString("img");

        // setting values to each view
        names.setText(name);
        descriptions.setText(description);
        teams.setText(studio);

        getSupportActionBar().setTitle(name);
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);

    }
}

