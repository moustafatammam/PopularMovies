package com.example.android.moviesplus.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    @SerializedName("results")
    private List<Review> reviews;

    public ReviewResponse(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
