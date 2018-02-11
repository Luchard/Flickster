package com.example.luchi.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String title = getIntent().getStringExtra("title");
        String overview = getIntent().getStringExtra("overview");
        String path = getIntent().getStringExtra("path");
        String release = getIntent().getStringExtra("release");
        Integer position = getIntent().getIntExtra("position",0);


        ImageView imageView = (ImageView) findViewById(R.id.ivMovieImage);
        imageView.setImageResource(0);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvRelease = (TextView) findViewById(R.id.tvRelease);
        TextView tvOverview = (TextView) findViewById(R.id.tvOverview);
        Picasso.with(this).load(path).fit().into(imageView);

        tvOverview.setText(overview);
        tvTitle.setText(title);
        tvRelease.setText(release);
    }
}
