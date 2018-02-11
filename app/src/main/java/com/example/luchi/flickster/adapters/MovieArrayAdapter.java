package com.example.luchi.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luchi.flickster.R;
import com.example.luchi.flickster.models.Movie;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by luchi on 2/10/2018.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView imageView;
    }



    public MovieArrayAdapter(@NonNull Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1 , movies);
    }

    @NonNull

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie , parent , false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        imageView.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);




        Picasso.with(getContext()).load(movie.getPosterPath()).into(imageView);
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());
        return convertView;
    }
}
