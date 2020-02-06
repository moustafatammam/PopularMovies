package com.example.android.popularmovies.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.model.Review;
import com.example.android.popularmovies.databinding.ItemReviewBinding;

import java.util.List;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.MovieReviewHolder> {

    private List<Review> review;
    private Context mContext;

    public MovieReviewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MovieReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReviewBinding itemReviewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_review, parent, false);
        return new MovieReviewHolder(itemReviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewHolder holder, int position) {
        holder.itemReviewBinding.setReview(review.get(position));
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(review.get(position).getAuthor().substring(0, 1).toUpperCase(), color);
        holder.itemReviewBinding.imageAuthor.setImageDrawable(drawable);

    }

    @Override
    public int getItemCount() {
        return review == null ? 0 : review.size();
    }

    public class MovieReviewHolder extends RecyclerView.ViewHolder{
        ItemReviewBinding itemReviewBinding;
        public MovieReviewHolder(@NonNull ItemReviewBinding itemReviewBinding) {
            super(itemReviewBinding.getRoot());
            this.itemReviewBinding = itemReviewBinding;
        }
    }

    public void setReview(List<Review> review) {
        this.review = review;
        notifyDataSetChanged();
    }
}
