package com.example.android.popularmovies.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "movie")
public class Movie  {

    @PrimaryKey
    private int id;

    private double popularity;


    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    private long voteCount;

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    private String posterPath;

    private boolean adult;

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    private String title;

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    private String originalTitle;

    private Boolean video;

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    private String originalLanguage;

    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    private List<Integer> genreIds;

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    private Double voteAverage;

    @SerializedName("overview")
    private String overView;

    @SerializedName("release_date")
    @ColumnInfo(name = "release_Date")
    private String releaseDate;

    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    private String runTime;

    @SerializedName("genres")
    private List<Genre> genres;

    private int isFavourite;

    public Movie( int isFavourite, double popularity, long voteCount, String posterPath, int id, boolean adult, String backdropPath, String title, String originalTitle, Boolean video, String originalLanguage, List<Integer> genreIds, Double voteAverage, String overView, String releaseDate, String runTime, List<Genre> genres) {
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.posterPath = posterPath;
        this.id = id;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.title = title;
        this.originalTitle = originalTitle;
        this.video = video;
        this.originalLanguage = originalLanguage;
        this.genreIds = genreIds;
        this.voteAverage = voteAverage;
        this.overView = overView;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.runTime = runTime;
        this.isFavourite = isFavourite;
    }






    public int isFavourite() {
        return isFavourite;
    }

    public void setFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };


    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        Movie movie = (Movie) obj;
        return movie.id == this.id;
    }
}
