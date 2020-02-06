package com.example.android.popularmovies.data.model;

import androidx.room.TypeConverter;

import com.example.android.popularmovies.SortMovieFilter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static String fromSortBy(SortMovieFilter sortBy) {
        Gson gson = new Gson();
        String  json = gson.toJson(sortBy);
        return json;
    }

    @TypeConverter
    public static SortMovieFilter toSortBy(String sortBy) {

        Type listType = new TypeToken<SortMovieFilter>() {}.getType();

        return new Gson().fromJson(sortBy, listType);
    }

    @TypeConverter
    public static String fromGenreIdList(List<Integer> genresId) {
        Gson gson = new Gson();
        String  json = gson.toJson(genresId);
        return json;
    }

    @TypeConverter
    public static List<Integer> toGenresIdList(String genreId) {
        if (genreId == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Integer>>() {}.getType();

        return new Gson().fromJson(genreId, listType);
    }

    @TypeConverter
    public static String fromGenresList(List<Genre> genres) {
        return gson.toJson(genres);
    }

    @TypeConverter
    public static List<Genre> toGenresList(String genres) {
        if (genres == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Genre>>() {}.getType();

        return gson.fromJson(genres, listType);
    }
}
