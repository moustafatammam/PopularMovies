package com.example.android.popularmovies.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.databinding.ItemCastBinding;
import com.example.android.popularmovies.data.model.Credits;

import java.util.List;

public class MovieCastAdapter extends RecyclerView.Adapter<MovieCastAdapter.CastViewHolder> {

    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";


    private List<Credits> casts;
    private Context mContext;

    public MovieCastAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCastBinding itemCastBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cast, parent, false);
        return new CastViewHolder(itemCastBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        holder.itemCastBinding.setCast(casts.get(position));
    }

    @Override
    public int getItemCount() {
        return casts == null ? 0 : casts.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder{

        ItemCastBinding itemCastBinding;

        public CastViewHolder(@NonNull ItemCastBinding itemCastBinding) {
            super(itemCastBinding.getRoot());
            this.itemCastBinding = itemCastBinding;
        }
    }

    public void submitList(List<Credits> casts) {
        this.casts = casts;
        notifyDataSetChanged();
    }


}
