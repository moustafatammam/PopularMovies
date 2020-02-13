package com.example.android.popularmovies.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.android.popularmovies.data.api.MoviesService;
import com.example.android.popularmovies.data.api.NetworkStatus;
import com.example.android.popularmovies.data.database.LocalMovieCache;
import com.example.android.popularmovies.data.database.MovieBoundaryCallback;
import com.example.android.popularmovies.data.model.Credits;
import com.example.android.popularmovies.data.model.Movie;
import com.example.android.popularmovies.data.model.Review;
import com.example.android.popularmovies.data.model.Video;

import java.util.List;

public class Repository {

    private static final String TAG = Repository.class.getSimpleName();
    private MoviesService mMoviesService;
    private static Repository sRepoInstance;
    private static final int MOVIE_LIST_PAGE_SIZE = 20;
    private LocalMovieCache localMovieCache;
    private MovieBoundaryCallback movieBoundaryCallback;



    public MutableLiveData<NetworkStatus> networkState = new MutableLiveData<>();


    private Repository(MoviesService mMoviesService, Context context) {
        this.mMoviesService = mMoviesService;
        localMovieCache = new LocalMovieCache(context);

    }

    public LiveData<PagedList<Movie>> loadMoviesFromDb(String sortBy) {
        movieBoundaryCallback = new MovieBoundaryCallback(sortBy, localMovieCache);

        DataSource.Factory<Integer, Movie> dataSourceFactory = localMovieCache.getPagedMovies(sortBy);
        LiveData<PagedList<Movie>> pagedListLiveData = new LivePagedListBuilder(dataSourceFactory, MOVIE_LIST_PAGE_SIZE)
                .setBoundaryCallback(movieBoundaryCallback)
                .build();
        return pagedListLiveData;
    }


    public LiveData<Movie> getMovieDetails(int movieId) {
        networkState.postValue(NetworkStatus.LOADING_IS_RUNNING);
        return localMovieCache.getMovieDetailsFromDb(movieId);
    }


    public LiveData<List<Credits>> getCreditsFromDb(int movieId) {
        localMovieCache.creditsRequestAndSave(movieId, mMoviesService);
        return localMovieCache.getMovieCreditsFromDb(movieId);
    }
    public LiveData<List<Video>> getTrailersFromDb(int movieId) {
        localMovieCache.trailersRequestAndSave(movieId, mMoviesService);
        return localMovieCache.getMovieTrailersFromDb(movieId);
    }
    public LiveData<List<Review>> getReviewsFromDb(int movieId) {
        localMovieCache.reviewsRequestAndSave(movieId, mMoviesService);
        return localMovieCache.getMovieReviewsFromDb(movieId);
    }

    public void setFavouriteMovieToDb(int movieId){
        localMovieCache.setFavouriteMovie(movieId);
    }
    public void removeFavouriteMovieFromDb(int movieId){
        localMovieCache.removeFavouriteMovie(movieId);
    }
    public LiveData<List<Movie>> getAllFavouriteMoviesFromDb(){
        return localMovieCache.getFavouriteMovies();
    }





    public static Repository getsRepoInstance(MoviesService mMoviesService, Context context) {
        if (sRepoInstance == null) {
            synchronized (Repository.class) {
                if (sRepoInstance == null) {
                    sRepoInstance = new Repository(mMoviesService, context);
                }
            }
        }
        return sRepoInstance;
    }

}
