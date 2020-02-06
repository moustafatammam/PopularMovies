package com.example.android.popularmovies.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.android.popularmovies.SortMovieFilter;
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



    public MutableLiveData<NetworkStatus> networkState = new MutableLiveData<>();


    private Repository(MoviesService mMoviesService) {
        this.mMoviesService = mMoviesService;

    }

    public LiveData<PagedList<Movie>> loadMoviesFromDb(SortMovieFilter sortBy, Context context) {
        LocalMovieCache localMovieCache = new LocalMovieCache(context);
        MovieBoundaryCallback movieBoundaryCallback = new MovieBoundaryCallback(sortBy, localMovieCache);

        DataSource.Factory<Integer, Movie> dataSourceFactory = localMovieCache.getPagedMovies(sortBy);
        LiveData<PagedList<Movie>> pagedListLiveData = new LivePagedListBuilder(dataSourceFactory, MOVIE_LIST_PAGE_SIZE)
                .setBoundaryCallback(movieBoundaryCallback)
                .build();
        return pagedListLiveData;
    }



    public LiveData<Movie> getMovieDetails(int movieId, Context context) {
        networkState.postValue(NetworkStatus.LOADING_IS_RUNNING);
        LocalMovieCache localMovieCache = new LocalMovieCache(context);
        return localMovieCache.getMovieDetailsFromDb(movieId);
    }


    public LiveData<List<Credits>> getCreditsFromDb(int movieId, Context context) {
        LocalMovieCache localMovieCache = new LocalMovieCache(context);
        localMovieCache.creditsRequestAndSave(movieId, mMoviesService);
        return localMovieCache.getMovieCreditsFromDb(movieId);
    }
    public LiveData<List<Video>> getTrailersFromDb(int movieId, Context context) {
        LocalMovieCache localMovieCache = new LocalMovieCache(context);
        localMovieCache.trailersRequestAndSave(movieId, mMoviesService);
        return localMovieCache.getMovieTrailersFromDb(movieId);
    }
    public LiveData<List<Review>> getReviewsFromDb(int movieId, Context context) {
        LocalMovieCache localMovieCache = new LocalMovieCache(context);
        localMovieCache.reviewsRequestAndSave(movieId, mMoviesService);
        return localMovieCache.getMovieReviewsFromDb(movieId);
    }



    public static Repository getsRepoInstance(MoviesService mMoviesService) {
        if (sRepoInstance == null) {
            synchronized (Repository.class) {
                if (sRepoInstance == null) {
                    sRepoInstance = new Repository(mMoviesService);
                }
            }
        }
        return sRepoInstance;
    }

}
