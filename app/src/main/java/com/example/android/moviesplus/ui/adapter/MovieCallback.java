package com.example.android.moviesplus.ui.adapter;

import android.view.View;

import com.example.android.moviesplus.data.model.Movie;

public interface MovieCallback {

    void onMovieClicked(Movie movie, View view);

    void onFavouriteMovieClicked(int isFavourite, Movie movie, View view);


}
