package com.example.android.popularmovies.data.database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.example.android.popularmovies.data.api.ApiClient;
import com.example.android.popularmovies.data.api.MoviesService;
import com.example.android.popularmovies.data.model.Movie;
import com.example.android.popularmovies.data.model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieBoundaryCallback extends PagedList.BoundaryCallback<Movie> {
    private static final String TAG = MovieBoundaryCallback.class.getSimpleName();

    private int lastPageRequestedPop = 1;
    private int lastPageRequestedTop = 1;
    private int lastPageRequestedNow = 1;

    private boolean isRequestInProgress = false;

    private LocalMovieCache mLocalMovieCache;

    private final MoviesService moviesService = ApiClient.getServiceInstance();

    private String sortBy;

    private List<Movie> movies;

    public MovieBoundaryCallback(String sortBy, LocalMovieCache mLocalMovieCache) {
        this.sortBy = sortBy;
        this.mLocalMovieCache = mLocalMovieCache;
    }

    public interface InsertCallback {
        void insertFinished();
    }


    @Override
    public void onZeroItemsLoaded() {
        requestAndSaveData(sortBy,  moviesService);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Movie itemAtEnd) {
        requestAndSaveData(sortBy, moviesService);
    }


    private void requestAndSaveData(String sortBy, MoviesService moviesService) {
        Log.d(TAG, "request");

        if (isRequestInProgress) {
            return;
        }
        isRequestInProgress = true;


        Call<MovieResponse> call;
            call = moviesService.getMovies(sortBy, lastPageRequestedPop);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.d(TAG, "calling api");
                if (response.body() != null) {
                    movies = response.body().getResults();

                    mLocalMovieCache.insertMoviesToDb(movies, () -> {
                            lastPageRequestedPop++;
                        isRequestInProgress = false;
                    });
                    Log.d(TAG, "calling done");
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "failed to get data");
            }
        });
    }
}
