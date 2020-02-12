package com.example.android.popularmovies.data.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.android.popularmovies.SortMovieFilter;
import com.example.android.popularmovies.data.api.ApiClient;
import com.example.android.popularmovies.data.api.MoviesService;
import com.example.android.popularmovies.data.api.NetworkStatus;
import com.example.android.popularmovies.data.model.Movie;
import com.example.android.popularmovies.data.model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagedKeyedMovieDataSource extends PageKeyedDataSource<Integer, Movie> {

    private final MoviesService moviesService = ApiClient.getServiceInstance();

    public MutableLiveData<NetworkStatus> networkState = new MutableLiveData<>();

    private final SortMovieFilter sortBy;

    public PagedKeyedMovieDataSource(SortMovieFilter sortBy){
        this.sortBy = sortBy;
    }



    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {


        networkState.postValue(NetworkStatus.LOADING_IS_RUNNING);

        Call<MovieResponse> call;

        if (sortBy == SortMovieFilter.POPULAR) {
            call = moviesService.getPopularMovies(1);
        } else {
            call = moviesService.getTopRatedMovies(1);
        }

        call.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                List<Movie> movies = response.body().getResults();

                callback.onResult(movies, null,  2);

                networkState.postValue(NetworkStatus.LOADING_SUCCESSFUL);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                networkState.postValue(NetworkStatus.error(t != null ? t.getMessage() : "unknown error"));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

        networkState.postValue(NetworkStatus.LOADING_IS_RUNNING);

        Call<MovieResponse> call;
        if (sortBy == SortMovieFilter.POPULAR) {
            call = moviesService.getPopularMovies(params.key);
        } else {
            call = moviesService.getTopRatedMovies(params.key);
        }

        call.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                List<Movie> movies = response.body().getResults();

                callback.onResult(movies, params.key+1);

                networkState.postValue(NetworkStatus.LOADING_SUCCESSFUL);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                networkState.postValue(NetworkStatus.error(t != null ? t.getMessage() : "unknown error"));
            }
        });
    }
}
