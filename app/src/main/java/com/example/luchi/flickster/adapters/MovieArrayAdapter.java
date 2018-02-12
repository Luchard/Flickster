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
int orientation;
    private static class ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView imageView;
    }



    public MovieArrayAdapter(@NonNull Context context, List<Movie> movies , int orientation) {
        super(context, android.R.layout.simple_list_item_1 , movies);
        this.orientation = orientation;
    }

    @NonNull

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie , parent , false);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.ivMovieImage);
           // imageView.setImageResource(0);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }





        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.imageView);
            // ...
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.imageView);
            // ...
        }


        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
        return convertView;
    }
}
