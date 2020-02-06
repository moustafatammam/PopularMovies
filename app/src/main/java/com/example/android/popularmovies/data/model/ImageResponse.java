package com.example.android.popularmovies.data.model;

import com.google.gson.annotations.SerializedName;

public final class ImageResponse {


    @SerializedName("images")
    private Image imageConfegurations;

    public ImageResponse(Image imageConfegurations) {
        this.imageConfegurations = imageConfegurations;
    }

    public Image getImageConfegurations() {
        return imageConfegurations;
    }

    public void setImageConfegurations(Image imageConfegurations) {
        this.imageConfegurations = imageConfegurations;
    }
}
