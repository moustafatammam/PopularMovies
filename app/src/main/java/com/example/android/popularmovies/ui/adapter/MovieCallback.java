package com.example.android.popularmovies.ui.adapter;

import android.view.View;

import com.example.android.popularmovies.data.model.Movie;

public interface MovieCallback {

    void onMovieClicked(Movie movie, View view);

}
