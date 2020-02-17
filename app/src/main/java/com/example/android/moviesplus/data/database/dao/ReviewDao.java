package com.example.android.moviesplus.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.android.moviesplus.data.model.Review;

import java.util.List;

@Dao
public interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReview(List<Review> reviewList);

    @Transaction
    @Query("SELECT * FROM review WHERE review.movie_id = :movieId")
    LiveData<List<Review>> getReview(int movieId);
}
