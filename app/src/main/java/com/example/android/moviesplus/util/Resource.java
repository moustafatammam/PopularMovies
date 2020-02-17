package com.example.android.moviesplus.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.moviesplus.data.api.Status;

public class Resource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success (@Nullable T data){
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data){
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data){
        return new Resource<>(Status.LOADING, data, null);
    }
}
