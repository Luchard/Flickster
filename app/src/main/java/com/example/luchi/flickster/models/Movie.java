package com.example.luchi.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by luchi on 2/10/2018.
 */

public class Movie {
    public String getReleaseDate() {
        return releaseDate;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getBackdropPath() {

        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }
String backdropPath ;
    String posterPath;
    String originalTitle;
    String overview;
    String releaseDate;
    boolean adult ;
    String originalLanguage;
    Integer  voteCount;
    boolean  video ;
    double    voteAverage ;
    double   popularity;

    public Movie(JSONObject jsonObject) {
        try {

            this.posterPath = jsonObject.getString("poster_path");
            this.backdropPath = jsonObject.getString("backdrop_path");
            this.originalTitle = jsonObject.getString("original_title");
            this.overview = jsonObject.getString("overview");
            this.releaseDate = jsonObject.getString("release_date");
            this.adult = jsonObject.getBoolean("adult");
            this.originalLanguage = jsonObject.getString("original_language");
            this.popularity = jsonObject.getDouble("popularity");
            this.voteAverage = jsonObject.getDouble("vote_average");
            this.video = jsonObject.getBoolean("video");
            this.voteCount = jsonObject.getInt("vote_count");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array){
ArrayList<Movie> results = new ArrayList<>();
for (int x = 0 ; x < array.length() ; x++){

    try {
        results.add(new Movie(array.getJSONObject(x)));
    } catch (JSONException e) {
        e.printStackTrace();
    }
}
return results;
    }
}
