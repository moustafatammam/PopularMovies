package com.example.android.moviesplus.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.android.moviesplus.data.model.Credits;

import java.util.List;

@Dao
public interface CastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCast(List<Credits> castList);

    @Transaction
    @Query("SELECT * FROM CREDITS WHERE credits.movie_id = :movieId")
    LiveData<List<Credits>> getCredits(int movieId);
}
