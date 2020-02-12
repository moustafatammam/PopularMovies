package com.example.android.popularmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.popularmovies.data.Repository;
import com.example.android.popularmovies.data.api.ApiClient;
import com.example.android.popularmovies.data.api.MoviesService;
import com.example.android.popularmovies.data.model.Movie;

import java.util.List;

public class FavouriteMoviesViewModel extends AndroidViewModel {

    private LiveData<List<Movie>> favouriteMovies;
    public FavouriteMoviesViewModel(@NonNull Application application) {
        super(application);
        MoviesService mMoviesService = ApiClient.getServiceInstance();
        Repository mRepository = Repository.getsRepoInstance(mMoviesService, application);

        favouriteMovies = mRepository.getAllFavouriteMoviesFromDb();
    }



    public LiveData<List<Movie>> getFavouriteMovies() {
        return favouriteMovies;
    }
}
