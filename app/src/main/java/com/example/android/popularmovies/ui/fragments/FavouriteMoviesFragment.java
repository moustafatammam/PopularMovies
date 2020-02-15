package com.example.android.popularmovies.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.model.Movie;
import com.example.android.popularmovies.databinding.FavouriteMoviesFragmentBinding;
import com.example.android.popularmovies.ui.adapter.FavouriteMoviesAdapter;
import com.example.android.popularmovies.ui.adapter.MovieCallback;
import com.example.android.popularmovies.viewmodel.FavouriteMoviesViewModel;

import java.util.List;

public class FavouriteMoviesFragment extends Fragment {

    private FavouriteMoviesViewModel mViewModel;
    private FavouriteMoviesFragmentBinding favouriteMoviesFragment;
    private FavouriteMoviesAdapter mFavouriteMovieAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        favouriteMoviesFragment = DataBindingUtil.inflate(inflater, R.layout.favourite_movies_fragment, container, false);
        return favouriteMoviesFragment.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FavouriteMoviesViewModel.class);
        RecyclerView favouriteRecyclerView = favouriteMoviesFragment.favouriteMoviesRecycleView;
        mFavouriteMovieAdapter = new FavouriteMoviesAdapter(getContext(), new FavoriteMovieListener());
        favouriteRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        favouriteRecyclerView.setNestedScrollingEnabled(true);
        favouriteRecyclerView.setAdapter(mFavouriteMovieAdapter);

        mViewModel.getFavouriteMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                mFavouriteMovieAdapter.submitList(movies);
            }
        });


    }

    private class FavoriteMovieListener implements MovieCallback {

        @Override
        public void onMovieClicked(Movie movie, View view) {
            Navigation.findNavController(view).navigate(FavouriteMoviesFragmentDirections.actionFavouriteMoviesFragmentToMovieDetailsFragment(movie.getId()));
        }

        @Override
        public void onFavouriteMovieClicked(int isFavourite, int movieId, View view) {
        }

    }
}
