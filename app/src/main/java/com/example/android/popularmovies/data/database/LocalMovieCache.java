package com.example.android.popularmovies.data.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.android.popularmovies.SortMovieFilter;
import com.example.android.popularmovies.data.api.MoviesService;
import com.example.android.popularmovies.data.api.NetworkStatus;
import com.example.android.popularmovies.data.model.Credits;
import com.example.android.popularmovies.data.model.CreditsResponse;
import com.example.android.popularmovies.data.model.Movie;
import com.example.android.popularmovies.data.model.Review;
import com.example.android.popularmovies.data.model.ReviewResponse;
import com.example.android.popularmovies.data.model.Video;
import com.example.android.popularmovies.data.model.VideoResponse;
import com.example.android.popularmovies.util.AppExecutors;
import com.example.android.popularmovies.util.GenreMapping;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalMovieCache {

    private static final String TAG = LocalMovieCache.class.getSimpleName();

    private AppExecutors mExecutors = AppExecutors.getInstance();

    private MovieDatabase movieDatabase;

    private List<Credits> credits;
    private List<Video> trailers;
    private List<Review> reviews;
    private Movie movie;

    public MutableLiveData<NetworkStatus> networkState = new MutableLiveData<>();


    private int insertionOrder = 0;


    public LocalMovieCache(Context context) {
        movieDatabase = MovieDatabase.getInstance(context);

    }

    public void insertMoviesToDb(List<Movie> movies, SortMovieFilter sortBy, MovieBoundaryCallback.InsertCallback insertCallback) {
        mExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "inserting movies");
                for (Movie movie : movies) {
                    movie.setSortBy(sortBy);
                    movie.setSortingId(insertionOrder++);
                    GenreMapping.setGenres(movie);
                }
                movieDatabase.movieDao().insertMovie(movies);
                Log.d(TAG, "inserting movies finished");
                insertCallback.insertFinished();
            }
        });
    }




    private void insertCreditsToDb(List<Credits> credits, int movieId) {
        mExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                for (Credits credit : credits) {
                    credit.setMovieId(movieId);
                }
                movieDatabase.castDao().insertCast(credits);
            }
        });
    }
    private void insertTrailersTob(List<Video> trailers, int movieId) {
        mExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                for (Video trailer : trailers) {
                    trailer.setMovieId(movieId);
                }
                movieDatabase.trailerDao().insertTrailer(trailers);
            }
        });
    }
    private void insertReviewsToDb(List<Review> reviews, int movieId) {
        mExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                for (Review review : reviews) {
                    review.setMovieId(movieId);
                }
                movieDatabase.reviewDao().insertReview(reviews);
            }
        });
    }


    public void creditsRequestAndSave(int movieId, MoviesService mMoviesService) {
        networkState.postValue(NetworkStatus.LOADING_IS_RUNNING);

        Call<CreditsResponse> call = mMoviesService.getMovieCredits(movieId);
        call.enqueue(new Callback<CreditsResponse>() {
            @Override
            public void onResponse(Call<CreditsResponse> call, Response<CreditsResponse> response) {
                if (response.body() != null) {
                    credits = response.body().getCast();
                    insertCreditsToDb(credits, movieId);

                } else {
                    Log.e(this.getClass().getSimpleName(), "error");
                }
            }

            @Override
            public void onFailure(Call<CreditsResponse> call, Throwable t) {
                networkState.postValue(NetworkStatus.error(t.getMessage()));

            }
        });

    }
    public void trailersRequestAndSave(int movieId, MoviesService mMoviesService) {
        networkState.postValue(NetworkStatus.LOADING_IS_RUNNING);

        Call<VideoResponse> call = mMoviesService.getVideos(movieId);
        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (response.body() != null) {
                    trailers = response.body().getVideos();
                    insertTrailersTob(trailers, movieId);

                } else {
                    Log.e(this.getClass().getSimpleName(), "error");
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                networkState.postValue(NetworkStatus.error(t.getMessage()));

            }
        });

    }

    public void reviewsRequestAndSave(int movieId, MoviesService mMoviesService) {
        networkState.postValue(NetworkStatus.LOADING_IS_RUNNING);

        Call<ReviewResponse> call = mMoviesService.getReviews(movieId);
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if (response.body() != null) {
                    reviews = response.body().getReviews();
                    insertReviewsToDb(reviews, movieId);

                } else {
                    Log.e(this.getClass().getSimpleName(), "error");
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                networkState.postValue(NetworkStatus.error(t.getMessage()));

            }
        });

    }


    public final DataSource.Factory<Integer, Movie> getPagedMovies(SortMovieFilter sortBy) {
        Log.d(TAG, "getting data");
        return movieDatabase.movieDao().getPagedMovies(sortBy);
    }

    public final LiveData<Movie> getMovieDetailsFromDb(int movieId) {
        Log.d(TAG, "getting movie details");
        return movieDatabase.movieDao().getMovie(movieId);
    }

    public final LiveData<List<Credits>> getMovieCreditsFromDb(int movieId) {
        Log.d(TAG, "getting movie credits");
        return movieDatabase.castDao().getCredits(movieId);
    }
    public final LiveData<List<Video>> getMovieTrailersFromDb(int movieId) {
        Log.d(TAG, "getting movie credits");
        return movieDatabase.trailerDao().getTrailers(movieId);
    }
    public final LiveData<List<Review>> getMovieReviewsFromDb(int movieId) {
        Log.d(TAG, "getting movie credits");
        return movieDatabase.reviewDao().getReview(movieId);
    }

}
