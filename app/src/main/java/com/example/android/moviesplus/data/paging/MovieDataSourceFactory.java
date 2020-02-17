package com.example.android.moviesplus.data.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.android.moviesplus.SortMovieFilter;
import com.example.android.moviesplus.data.model.Movie;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private MutableLiveData<PagedKeyedMovieDataSource> mutableLiveData;
    private SortMovieFilter sortBy;

    public MovieDataSourceFactory(SortMovieFilter sortBy){
        mutableLiveData = new MutableLiveData<>();
        this.sortBy = sortBy;
    }

    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        PagedKeyedMovieDataSource pagedKeyedMovieDataSource = new PagedKeyedMovieDataSource(sortBy);
        mutableLiveData.postValue(pagedKeyedMovieDataSource);
        return pagedKeyedMovieDataSource;
    }

    public MutableLiveData<PagedKeyedMovieDataSource> getMutableLiveData(){
        return mutableLiveData;
    }
}
