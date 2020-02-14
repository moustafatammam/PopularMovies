package com.example.android.popularmovies.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.api.NetworkStatus;
import com.example.android.popularmovies.data.model.Credits;
import com.example.android.popularmovies.data.model.Movie;
import com.example.android.popularmovies.data.model.Review;
import com.example.android.popularmovies.data.model.Video;
import com.example.android.popularmovies.databinding.FragmentMovieDetailsBinding;
import com.example.android.popularmovies.ui.activities.MainActivity;
import com.example.android.popularmovies.ui.adapter.MovieCastAdapter;
import com.example.android.popularmovies.ui.adapter.MovieReviewAdapter;
import com.example.android.popularmovies.ui.adapter.MovieTrailersAdapter;
import com.example.android.popularmovies.viewmodel.MovieDetailsViewModel;
import com.example.android.popularmovies.viewmodel.MovieDetailsViewModelFactory;

import java.util.List;
import java.util.Objects;


public class MovieDetailsFragment extends Fragment {

    private MovieDetailsViewModel movieDetailsViewModel;

    private FragmentMovieDetailsBinding fragmentMovieDetailsBinding;

    private MovieCastAdapter movieCastAdapter;
    private MovieReviewAdapter movieReviewAdapter;
    private MovieTrailersAdapter movieTrailersAdapter;

    private final static String YOUTUBE_LINK_TRAILER = "https://www.youtube.com/watch?v=";

    private MutableLiveData<NetworkStatus> networkStatus = new MutableLiveData<>();

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using data binding.
        fragmentMovieDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);

        return fragmentMovieDetailsBinding.getRoot();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        networkStatus.postValue(NetworkStatus.LOADING_IS_RUNNING);

        int movieId = MovieDetailsFragmentArgs.fromBundle(Objects.requireNonNull(getArguments())).getMovieId();

        // setup the detail viewModel and its data binding.
        movieDetailsViewModel = ViewModelProviders.of(this, new MovieDetailsViewModelFactory(getActivity().getApplication(), movieId)).get(MovieDetailsViewModel.class);
        fragmentMovieDetailsBinding.setMovieDetailViewModel(movieDetailsViewModel);
        fragmentMovieDetailsBinding.scrollView.setNestedScrollingEnabled(true);

        // setup the recycler views and their adapters for the cast, review and trailer movie details.
        setupCastDetail();
        setupReviewDetail();
        setupTrailerDetail();

        movieDetailsViewModel.getMovieDetailsLiveData().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                movieDetailsViewModel.setMovie(movie);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle(movie.getTitle());

            }
        });
        movieDetailsViewModel.getCreditsLiveData().observe(this, new Observer<List<Credits>>() {
            @Override
            public void onChanged(List<Credits> credits) {
                movieCastAdapter.submitList(credits);

            }
        });
        movieDetailsViewModel.getReviewLiveData().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                movieReviewAdapter.setReview(reviews);

            }
        });
        movieDetailsViewModel.getTrailerLiveData().observe(this, new Observer<List<Video>>() {
            @Override
            public void onChanged(List<Video> videos) {
                movieTrailersAdapter.setTrailers(videos);
            }
        });

    }

    private void setupCastDetail() {
        RecyclerView castRecyclerView = fragmentMovieDetailsBinding.listCast;
        castRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        movieCastAdapter = new MovieCastAdapter(getContext());
        castRecyclerView.setAdapter(movieCastAdapter);
    }

    private void setupReviewDetail() {
        RecyclerView reviewRecyclerView = fragmentMovieDetailsBinding.listReview;
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        movieReviewAdapter = new MovieReviewAdapter(getContext());
        reviewRecyclerView.setAdapter(movieReviewAdapter);
    }

    private void setupTrailerDetail() {
        RecyclerView trailerRecyclerView = fragmentMovieDetailsBinding.listTrailers;
        trailerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        movieTrailersAdapter = new MovieTrailersAdapter(getContext());
        trailerRecyclerView.setAdapter(movieTrailersAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.movie_details_share, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Movie movieDetails = movieDetailsViewModel.getMovieDetailsLiveData().getValue();
                Video movieTrailer = movieDetailsViewModel.getTrailerLiveData().getValue().get(0);
                Intent shareIntent = ShareCompat.IntentBuilder.from(getActivity())
                        .setType("text/plain")
                        .setSubject(movieDetails.getTitle() + "movie trailer")
                        .setText("check" + movieDetails.getTitle() + "movie trailer at" + Uri.parse(YOUTUBE_LINK_TRAILER + movieTrailer.getKey()))
                        .createChooserIntent();
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                if (shareIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(shareIntent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
