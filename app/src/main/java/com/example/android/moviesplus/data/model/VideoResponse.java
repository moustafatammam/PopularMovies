package com.example.android.moviesplus.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class VideoResponse {
    @SerializedName("results")
    private List<Video> videos;

    public VideoResponse(List<Video> videos) {
        this.videos = videos;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
