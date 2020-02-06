package com.example.android.popularmovies.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.android.popularmovies.SortMovieFilter;
import com.example.android.popularmovies.data.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(List<Movie> movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMoviedetails(Movie movie);


    @Transaction
    @Query("SELECT * FROM movie WHERE movie.sortBy = :sortBy ORDER BY sortingId")
    DataSource.Factory<Integer, Movie> getPagedMovies(SortMovieFilter sortBy);

    @Transaction
    @Query("SELECT * FROM movie WHERE movie.id = :movieId")
    LiveData<Movie> getMovie(int movieId);




}
