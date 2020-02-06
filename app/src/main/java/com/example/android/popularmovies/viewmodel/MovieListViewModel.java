package com.example.android.popularmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.PagedList;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.SortMovieFilter;
import com.example.android.popularmovies.data.Repository;
import com.example.android.popularmovies.data.api.ApiClient;
import com.example.android.popularmovies.data.api.MoviesService;
import com.example.android.popularmovies.data.model.Movie;

public class MovieListViewModel extends AndroidViewModel {

    private LiveData<PagedList<Movie>> pagedListLiveData;
    MutableLiveData<SortMovieFilter> sortBy = new MutableLiveData();

    private MutableLiveData<Integer> currentTitle = new MutableLiveData<>();


    public MovieListViewModel(@NonNull Application application) {
        super(application);
        sortBy.setValue(SortMovieFilter.POPULAR);

        currentTitle.setValue(R.string.action_popular);
        MoviesService mMoviesService = ApiClient.getServiceInstance();
        Repository mRepository = Repository.getsRepoInstance(mMoviesService);

        pagedListLiveData = Transformations.switchMap(sortBy, new Function<SortMovieFilter, LiveData<PagedList<Movie>>>() {
            @Override
            public LiveData<PagedList<Movie>> apply(SortMovieFilter sort) {
                return mRepository.loadMoviesFromDb(sort,application);
            }
        });
    }

    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public SortMovieFilter getCurrentSorting() {
        return sortBy.getValue();
    }

    public void setSortMoviesBy(int id) {
        SortMovieFilter filterType = null;
        Integer title = null;
        switch (id) {
            case R.id.popular_movies: {
                // check if already selected. no need to request API
                if (sortBy.getValue() == SortMovieFilter.POPULAR)
                    return;

                filterType = SortMovieFilter.POPULAR;
                title = R.string.action_popular;
                break;
            }
            case R.id.top_rated: {
                if (sortBy.getValue() == SortMovieFilter.TOP_RATED)
                    return;

                filterType = SortMovieFilter.TOP_RATED;
                title = R.string.action_top_rated;
                break;
            }
            case R.id.playing_now: {
                if (sortBy.getValue() == SortMovieFilter.NOW_PLAYING)
                    return;

                filterType = SortMovieFilter.NOW_PLAYING;
                title = R.string.action_now_playing;
                break;
            }

            default:
                throw new IllegalArgumentException("unknown sorting id");
        }
        sortBy.setValue(filterType);
        currentTitle.setValue(title);
    }

    public LiveData<Integer> getCurrentTitle() {
        return currentTitle;

    }
}
