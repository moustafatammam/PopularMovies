package com.example.android.moviesplus.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.moviesplus.R;
import com.example.android.moviesplus.data.model.Genre;
import com.example.android.moviesplus.data.model.Movie;
import com.example.android.moviesplus.databinding.ListItemMovieBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class MovieListAdapter extends PagedListAdapter<Movie, MovieListAdapter.MovieListViewHolder> {

    private Context mContext;

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";




    private final MovieCallback movieClickedListener;



    public MovieListAdapter(Context mContext, MovieCallback movieClickedListener){
        super(Movie.DIFF_CALLBACK);
        this.mContext = mContext;
        this.movieClickedListener = movieClickedListener;

    }




    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemMovieBinding listItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_movie, parent, false);

        return new MovieListViewHolder(listItemMovieBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        Movie movie = getItem(position);
        holder.listItemMovieBinding.setMovie(movie);
        holder.listItemMovieBinding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieClickedListener.onMovieClicked(movie, v);
            }
        });
        CheckBox checkBox = holder.listItemMovieBinding.favouriteButton;
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieClickedListener.onFavouriteMovieClicked(movie.isFavourite(), movie, v);
            }
        });
        if (movie != null && movie.isFavourite() == 1){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }
    }


    // creating a view holder for movies list
    public class MovieListViewHolder extends RecyclerView.ViewHolder{
        ListItemMovieBinding listItemMovieBinding;

        public MovieListViewHolder(@NonNull ListItemMovieBinding listItemMovieBinding) {
            super(listItemMovieBinding.getRoot());
            this.listItemMovieBinding = listItemMovieBinding;
        }
    }

    // a binding adapter for setting the poster image in the XML
    @BindingAdapter({"imageUrl"})
    public static void bindImage(ImageView imageView, String posterPath){
        Glide.with(imageView.getContext())
                .load(IMAGE_BASE_URL + posterPath)
                .placeholder(R.drawable.baseline_local_movies_24)
                .into(imageView);
    }

    // a binding adapter for chip group genres
    @BindingAdapter({"items"})
    public static void bindGenres(ChipGroup chips, List<Genre> genres){
        if (genres == null || chips.getChildCount() > 0) {
            return;
        }
        Context context = chips.getContext();
        for (Genre genre : genres){
            Chip chip = new Chip(context);
            chip.setText(genre.getGenre());
            chip.setChipBackgroundColorResource(android.R.color.darker_gray);
            chips.addView(chip);
        }
    }

}
