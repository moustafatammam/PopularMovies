package com.example.android.popularmovies.data.api;

import com.example.android.popularmovies.data.model.CreditsResponse;
import com.example.android.popularmovies.data.model.Image;
import com.example.android.popularmovies.data.model.Movie;
import com.example.android.popularmovies.data.model.MovieResponse;
import com.example.android.popularmovies.data.model.ReviewResponse;
import com.example.android.popularmovies.data.model.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {


    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("page") int page);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("page") int page);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("page") int page);

    @GET("discover/movie")
     Call<MovieResponse> getMovies(
             @Query("sort_by") String sortBy,
             @Query("page")int pageNum);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(
            @Path("movie_id")int id);

    @GET("movie/{movie_id}/credits")
    Call<CreditsResponse> getMovieCredits(
            @Path("movie_id")int id);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewResponse> getReviews(
            @Path("movie_id")int id);

    @GET("movie/{movie_id}/videos")
    Call<VideoResponse> getVideos(
            @Path("movie_id")int id);


    @GET("/configuration")
    Call<Image> getImageType();
}
