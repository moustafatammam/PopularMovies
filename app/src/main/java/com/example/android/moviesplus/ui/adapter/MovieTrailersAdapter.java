package com.example.android.moviesplus.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.moviesplus.R;
import com.example.android.moviesplus.data.model.Video;
import com.example.android.moviesplus.databinding.ItemTrailerBinding;

import java.util.List;

public class MovieTrailersAdapter extends RecyclerView.Adapter<MovieTrailersAdapter.MovieTrailerViewHolder>{

    private List<Video> trailers;
    private final static String YOUTUBE_LINK_THUMBNAIL = "https://img.youtube.com/vi/";
    private final static String YOUTUBE_LINK_TRAILER = "https://www.youtube.com/watch?v=";
    private Context mContext;

    public MovieTrailersAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MovieTrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTrailerBinding itemTrailerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_trailer, parent, false);
        return new MovieTrailerViewHolder(itemTrailerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTrailerViewHolder holder, int position) {
        holder.itemTrailerBinding.setVideo(trailers.get(position));
        holder.itemTrailerBinding.trailerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailers.get(position).getKey()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_LINK_TRAILER + trailers.get(position).getKey()));
                if(appIntent.resolveActivity(mContext.getPackageManager()) != null){
                    mContext.startActivity(appIntent);
                }else{
                    mContext.startActivity(webIntent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers == null ? 0 : trailers.size();
    }

    public class MovieTrailerViewHolder extends RecyclerView.ViewHolder{
        ItemTrailerBinding itemTrailerBinding;
        public MovieTrailerViewHolder(@NonNull ItemTrailerBinding itemTrailerBinding) {
            super(itemTrailerBinding.getRoot());
            this.itemTrailerBinding = itemTrailerBinding;
        }
    }

    public void setTrailers(List<Video> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @BindingAdapter({"thumbnail"})
    public static void bindThumbnail(ImageView imageView, String trailerKey){
        Glide.with(imageView.getContext())
                .load(YOUTUBE_LINK_THUMBNAIL + trailerKey + "/hqdefault.jpg")
                .into(imageView);
    }


}
