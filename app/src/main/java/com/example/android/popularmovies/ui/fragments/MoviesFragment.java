package com.example.android.popularmovies.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.model.Movie;
import com.example.android.popularmovies.databinding.FragmentPopularMoviesBinding;
import com.example.android.popularmovies.ui.activities.MainActivity;
import com.example.android.popularmovies.ui.adapter.MovieCallback;
import com.example.android.popularmovies.ui.adapter.MovieListAdapter;
import com.example.android.popularmovies.viewmodel.MovieListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MoviesFragment extends Fragment {

    private MovieListAdapter movieListAdapter;

    private MovieListViewModel movieListViewModel;

    private FragmentPopularMoviesBinding fragmentPopularMoviesBinding;

    private RecyclerView moviesRecyclerView;
    private MovieListener mMovieListener;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPopularMoviesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular_movies, container, false);
        return fragmentPopularMoviesBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        FloatingActionButton mFloatingActionButton = fragmentPopularMoviesBinding.backToTop;
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesRecyclerView.scrollToPosition(0);
            }
        });
        mMovieListener = new MovieListener();
        // creating an instance of the MovieListViewModel
        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        movieListViewModel.getCurrentTitle().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer title) {
                ((MainActivity) getActivity()).getSupportActionBar().setTitle(title);
            }
        });
        setupMovieList();
        populateUi();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.sorting_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        if (movieListViewModel.getCurrentSorting().equals("popularity.desc")) {
            menu.findItem(R.id.popular_movies).setChecked(true);
        } else {
            menu.findItem(R.id.top_rated).setChecked(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        movieListViewModel.setSortMoviesBy(item.getItemId());
        item.setChecked(true);
        return super.onOptionsItemSelected(item);
    }

    // populate the movie fragment with the data called from the api
    private void populateUi() {

        movieListViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                movieListAdapter.submitList(movies);
            }
        });

    }

    // setup the recycler view and its adapter for the movie list.
    private void setupMovieList() {
        moviesRecyclerView = fragmentPopularMoviesBinding.moviesRecycleView;
        movieListAdapter = new MovieListAdapter(getContext(), mMovieListener);
        moviesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        moviesRecyclerView.setNestedScrollingEnabled(true);
        moviesRecyclerView.setAdapter(movieListAdapter);

    }

    private class MovieListener implements MovieCallback {

        @Override
        public void onMovieClicked(Movie movie, View view) {
            Navigation.findNavController(view).navigate(MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movie.getId()));
        }

        @Override
        public void onFavouriteMovieClicked(int isFavourite, Movie movie, View view) {
            movieListViewModel.onFavouriteMovieClicked(isFavourite, movie);
        }

    }


}


