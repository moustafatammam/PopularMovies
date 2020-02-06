package com.example.android.popularmovies.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.android.popularmovies.data.model.Video;

import java.util.List;

@Dao
public interface TrailerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTrailer(List<Video> trailerList);

    @Transaction
    @Query("SELECT * FROM VIDEO WHERE video.movie_id = :movieId")
    LiveData<List<Video>> getTrailers(int movieId);
}
