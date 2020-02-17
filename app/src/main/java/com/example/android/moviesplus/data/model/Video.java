package com.example.android.moviesplus.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "video", foreignKeys = @ForeignKey(entity = Movie.class, parentColumns = "id", childColumns = "movie_id"), indices = {@Index(value = {"movie_id"})})
public final class Video {

    @PrimaryKey
    @NonNull
    private String id;

    @SerializedName("iso_639_1")
    @ColumnInfo(name = "iso_639_1")
    private String iso;

    private String key;

    @ColumnInfo(name = "sorting_id")
    private int sortingId;

    private String name;

    @ColumnInfo(name = "movie_id")
    private int movieId;

    private String site;

    private int size;

    private String type;

    public Video(int sortingId, String id, String iso, String key, String name, String site, int size, String type, int movieId) {
        this.id = id;
        this.iso = iso;
        this.key = key;
        this.name = name;
        this.site = site;
        this.size = size;
        this.type = type;
        this.movieId = movieId;
        this.sortingId = sortingId;
    }

    public int getSortingId() {
        return sortingId;
    }

    public void setSortingId(int sortingId) {
        this.sortingId = sortingId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
