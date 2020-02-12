package com.example.android.popularmovies.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.android.popularmovies.data.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insertMovie(List<Movie> movie);



    @Transaction
    @Query("SELECT * FROM movie ORDER BY popularity Desc")
     DataSource.Factory<Integer, Movie> getPagedMoviesPop();

    @Transaction
    @Query("SELECT * FROM movie ORDER BY vote_count DESC")
    DataSource.Factory<Integer, Movie> getPagedMoviesTop();

    @Transaction
    @Query("SELECT * FROM movie WHERE movie.id = :movieId")
    LiveData<Movie> getMovie(int movieId);

    @Query("SELECT * FROM movie WHERE isFavourite = 1")
    LiveData<List<Movie>> getFavouriteMovies();

    @Query("UPDATE movie SET isFavourite = 1 WHERE id = :movieId")
    int favouriteMovie(int movieId);

    @Query("UPDATE movie SET isFavourite = 0 WHERE id = :movieId")
    int unFavouriteMovie(int movieId);






}
