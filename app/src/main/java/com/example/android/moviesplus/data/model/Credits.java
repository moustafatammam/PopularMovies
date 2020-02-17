package com.example.android.moviesplus.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "credits", foreignKeys = @ForeignKey(entity = Movie.class, parentColumns = "id", childColumns = "movie_id"))
public final class Credits {

    @SerializedName("cast_id")
    @ColumnInfo(name = "cast_id")
    private int castId;

    @SerializedName("credit_id")
    @PrimaryKey
    @ColumnInfo(name = "credit_id")
    @NonNull
    private String creditId;

    @ColumnInfo(name = "sorting_id")
    private int sortingId;

    private String character;

    private int gender;

    private int id;

    @ColumnInfo(name = "movie_id")
    private int movieId;

    private String name;

    private int order;

    @SerializedName("profile_path")
    @ColumnInfo(name = "profile_path")
    private String profilePath;

    public Credits(int sortingId, int castId, String creditId, String character, int gender, int id, String name, int order, String profilePath, int movieId) {
        this.castId = castId;
        this.creditId = creditId;
        this.character = character;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.order = order;
        this.profilePath = profilePath;
        this.movieId = movieId;
        this.sortingId = sortingId;
    }

    public int getSortingId() {
        return sortingId;
    }

    public void setSortingId(int sortingId) {
        this.sortingId = sortingId;
    }

    public int getCastId() {
        return castId;
    }

    public void setCastId(int castId) {
        this.castId = castId;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
