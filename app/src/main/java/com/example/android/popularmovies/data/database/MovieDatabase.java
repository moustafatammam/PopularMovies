package com.example.android.popularmovies.data.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.android.popularmovies.data.database.dao.CastDao;
import com.example.android.popularmovies.data.database.dao.MovieDao;
import com.example.android.popularmovies.data.database.dao.ReviewDao;
import com.example.android.popularmovies.data.database.dao.TrailerDao;
import com.example.android.popularmovies.data.model.Converters;
import com.example.android.popularmovies.data.model.Credits;
import com.example.android.popularmovies.data.model.Movie;
import com.example.android.popularmovies.data.model.Review;
import com.example.android.popularmovies.data.model.Video;

@Database(entities = {Movie.class, Credits.class, Video.class, Review.class},
version = 1,
exportSchema = false)
@TypeConverters(Converters.class)
public abstract class MovieDatabase extends RoomDatabase {

    private static final String LOG_TAG = MovieDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "movielist";
    private static MovieDatabase sDatabaseInstance;


    public static MovieDatabase getInstance(Context context){
        if(sDatabaseInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "creating a new database instance");
                sDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDatabase.class, MovieDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "getting the database instance");
        return sDatabaseInstance;
    }

    public abstract MovieDao movieDao();

    public abstract CastDao castDao();

    public abstract TrailerDao trailerDao();

    public abstract ReviewDao reviewDao();


}
