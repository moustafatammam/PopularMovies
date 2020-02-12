package com.example.android.popularmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.popularmovies.data.Repository;
import com.example.android.popularmovies.data.api.ApiClient;
import com.example.android.popularmovies.data.api.MoviesService;
import com.example.android.popularmovies.data.model.Credits;
import com.example.android.popularmovies.data.model.Movie;
import com.example.android.popularmovies.data.model.Review;
import com.example.android.popularmovies.data.model.Video;

import java.util.List;

public class MovieDetailsViewModel extends AndroidViewModel {

    private final LiveData<Movie> movieDetailsLiveData;
    private final LiveData<List<Credits>> creditsLiveData;
    private final LiveData<List<Review>> reviewLiveData;
    private final LiveData<List<Video>> trailerLiveData;

    public ObservableField<Movie> movie = new ObservableField<>();


    private int movieId;

    public MovieDetailsViewModel(@NonNull Application application, int movieId) {
        super(application);
        this.movieId = movieId;
        MoviesService mMoviesService = ApiClient.getServiceInstance();
        Repository repository = Repository.getsRepoInstance(mMoviesService, application);
        movieDetailsLiveData = repository.getMovieDetails(movieId);
        creditsLiveData = repository.getCreditsFromDb(movieId);
        reviewLiveData = repository.getReviewsFromDb(movieId);
        trailerLiveData = repository.getTrailersFromDb(movieId);
    }


    public LiveData<Movie> getMovieDetailsLiveData() {
        return movieDetailsLiveData;
    }


    public void setMovie(Movie movie) {
        this.movie.set(movie);
    }

    public LiveData<List<Review>> getReviewLiveData() {
        return reviewLiveData;
    }

    public LiveData<List<Credits>> getCreditsLiveData() {
        return creditsLiveData;
    }

    public LiveData<List<Video>> getTrailerLiveData() {
        return trailerLiveData;
    }
}
