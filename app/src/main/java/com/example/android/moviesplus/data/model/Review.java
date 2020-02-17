package com.example.android.moviesplus.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "review", foreignKeys = @ForeignKey(entity = Movie.class, parentColumns = "id", childColumns = "movie_id"), indices = {@Index(value = {"movie_id"})})
public final class Review {

    private String author;

    private String content;

    @PrimaryKey
    @NonNull
    private String id;

    private String url;
    @ColumnInfo(name = "sorting_id")
    private int sortingId;

    @ColumnInfo(name = "movie_id")
    private int movieId;

    public Review(int sortingId, String author, String content, String id, String url, int movieId) {
        this.author = author;
        this.content = content;
        this.id = id;
        this.url = url;
        this.movieId = movieId;
        this.sortingId =sortingId;
    }

    public int getSortingId() {
        return sortingId;
    }

    public void setSortingId(int sortingId) {
        this.sortingId = sortingId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
