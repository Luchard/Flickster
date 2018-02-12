package com.example.luchi.flickster;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.luchi.flickster.adapters.MovieArrayAdapter;
import com.example.luchi.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {
ArrayList<Movie> movies;
MovieArrayAdapter moviearrayadapter;
ListView listView;
ListView listviewLand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        listView = (ListView) findViewById(R.id.lvMovies);
        listviewLand = (ListView) findViewById(R.id.lvItemLand);
        movies = new ArrayList<>();
        int orientation = getResources().getConfiguration().orientation;
        moviearrayadapter = new MovieArrayAdapter(this , movies , orientation);



        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            listView.setAdapter(moviearrayadapter);
            setupViewListener();
            // ...
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            listviewLand.setAdapter(moviearrayadapter);
            setupViewListenerLand();
            // ...
        }



       String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url , new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;
                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    moviearrayadapter.notifyDataSetChanged();

                    Log.d("DEBUG", movies.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    private void setupViewListener(){
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {


                    public void onItemClick(AdapterView<?> arg0,
                                            View arg1, int arg2, long arg3) {

                    String title =    movies.get(arg2).getOriginalTitle();
                       String overview =  movies.get(arg2).getOverview();
                        String path = movies.get(arg2).getPosterPath();
                        String release = movies.get(arg2).getReleaseDate();
                        launchComposeView(title, overview , path , release , arg2);

                    }
                });
    }

    private void setupViewListenerLand(){
        listviewLand.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {


                    public void onItemClick(AdapterView<?> arg0,
                                            View arg1, int arg2, long arg3) {

                        String title =    movies.get(arg2).getOriginalTitle();
                        String overview =  movies.get(arg2).getOverview();
                        String path = movies.get(arg2).getPosterPath();
                        String release = movies.get(arg2).getReleaseDate();
                        launchComposeView(title, overview , path , release , arg2);

                    }
                });
    }

    public void launchComposeView(String title, String Overview, String path , String release , int position) {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(MovieActivity.this, DetailActivity.class);

        i.putExtra("position",position);
        i.putExtra("title",title);
        i.putExtra("overview",Overview);
        i.putExtra("path",path);
        i.putExtra("release" , release);
        // i.putExtra("in_reply_to", "george");
        //   i.putExtra("code", 400);
        startActivity(i); // brings up the second activity

        //   onActivityResult(REQUEST_CODE, RESULT_OK,i);
    }
}
