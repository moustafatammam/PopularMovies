package com.example.android.popularmovies.util;

import com.example.android.popularmovies.data.model.Genre;
import com.example.android.popularmovies.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class GenreMapping<T> {

    public static String genres(int genreId){

        switch(genreId){
            case 28:
                return "Action";
            case 12:
                return "Adventure";
            case 16:
                return "Animation";
            case 35:
                return "Comedy";
            case 80:
                return "Crime";
            case 99:
                return "Documentary";
            case 18:
                return "Drama";
            case 10751:
                return "Family";
            case 14:
                return "Fantasy";
            case 36:
                return "History";
            case 27:
                return "Horror";
            case 10402:
                return "Music";
            case 9648:
                return "Mystery";
            case 10749:
                return "Romance";
            case 878:
                return "Science Fiction";
            case 53:
                return "TV Movie";
            case 10752:
                return "Thriller";
            case 37:
                return "War";
            case 10770:
                return "Western";
                default:
                   return  "non specified genre";
        }
    }
    public static void setGenres(Movie movie){
       List<Integer> genreId =  movie.getGenreIds();
       List<Genre> genres = new ArrayList<>();
       for(int i=0 ; i < genreId.size(); i++){
           Genre genre = new Genre();
           genre.setId(genreId.get(i));
           genre.setGenre(genres(genreId.get(i)));
           genres.add(genre);
       }
       movie.setGenres(genres);
    }
}
