package com.example.android.popularmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.popularmovies.ui.adapter.MovieCastAdapter;

public class MovieDetailsViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int movieId;


    public MovieDetailsViewModelFactory(Application mApplication, int movieId) {
        this.mApplication = mApplication;
        this.movieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieDetailsViewModel(mApplication, movieId);
    }
}
