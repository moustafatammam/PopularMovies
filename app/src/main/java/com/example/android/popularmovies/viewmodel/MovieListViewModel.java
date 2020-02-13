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
import com.example.android.popularmovies.data.Repository;
import com.example.android.popularmovies.data.api.ApiClient;
import com.example.android.popularmovies.data.api.MoviesService;
import com.example.android.popularmovies.data.model.Movie;

public class MovieListViewModel extends AndroidViewModel {

    private LiveData<PagedList<Movie>> pagedListLiveData;
    MutableLiveData<String> sortBy = new MutableLiveData();
    private MoviesService mMoviesService;
    private Repository mRepository;
    //private boolean isFavourite;

    private MutableLiveData<Integer> currentTitle = new MutableLiveData<>();


    public MovieListViewModel(@NonNull Application application) {
        super(application);
        sortBy.setValue("popularity.desc");

        currentTitle.setValue(R.string.action_popular);
        mMoviesService = ApiClient.getServiceInstance();
        mRepository = Repository.getsRepoInstance(mMoviesService, application);


        pagedListLiveData = Transformations.switchMap(sortBy, new Function<String, LiveData<PagedList<Movie>>>() {
            @Override
            public LiveData<PagedList<Movie>> apply(String sort) {
                return mRepository.loadMoviesFromDb(sort);
            }
        });
    }

    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public String getCurrentSorting() {
        return sortBy.getValue();
    }

    public void setSortMoviesBy(int id) {
        String filterType = null;
        Integer title = null;
        switch (id) {
            case R.id.popular_movies: {
                // check if already selected. no need to request API
                if (sortBy.getValue().equals("popularity.desc"))
                    return;

                filterType = "popularity.desc";
                title = R.string.action_popular;
                break;
            }
            case R.id.top_rated: {
                if (sortBy.getValue().equals("vote_count.desc"))
                    return;

                filterType = "vote_count.desc";
                title = R.string.action_top_rated;
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

    public void onFavouriteMovieClicked(int isFavourite, int movieId){
        if (isFavourite != 1) {
            mRepository.setFavouriteMovieToDb(movieId);
        }else{
            mRepository.removeFavouriteMovieFromDb(movieId);
        }
    }


}
