package com.example.android.popularmovies.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public final class CreditsResponse {

    @SerializedName("cast")
    private List<Credits> cast = new ArrayList<>();


    public List<Credits> getCast() {
        return cast;
    }

    public void setCast(List<Credits> cast) {
        this.cast = cast;
    }
}
