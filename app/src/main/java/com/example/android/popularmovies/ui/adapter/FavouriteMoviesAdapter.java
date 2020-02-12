package com.example.android.popularmovies.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.model.Movie;
import com.example.android.popularmovies.databinding.FavouriteItemMovieBinding;

import java.util.List;

public class FavouriteMoviesAdapter extends RecyclerView.Adapter<FavouriteMoviesAdapter.FavouriteMovieViewHolder> {

    private List<Movie> movies;
    private Context mContext;

    public FavouriteMoviesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FavouriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavouriteItemMovieBinding favouriteItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.favourite_item_movie, parent, false);
        return new FavouriteMovieViewHolder(favouriteItemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteMovieViewHolder holder, int position) {
        holder.favouriteItemMovieBinding.setMovie(movies.get(position));

    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public class FavouriteMovieViewHolder extends RecyclerView.ViewHolder{

        FavouriteItemMovieBinding favouriteItemMovieBinding;

        public FavouriteMovieViewHolder(@NonNull FavouriteItemMovieBinding favouriteItemMovieBinding) {
            super(favouriteItemMovieBinding.getRoot());
            this.favouriteItemMovieBinding = favouriteItemMovieBinding;
        }
    }

    public void submitList(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }
}
