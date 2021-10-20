package com.whatsapp.chattema;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter  extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<String> themes;
    private Context context;
    private OnDataSelected onDataSelected;


    public ImageAdapter(Context context, List<String> themes, OnDataSelected onDataSelected) {
        this.context = context;
        this.onDataSelected = onDataSelected;
        this.themes = themes;
    }

    public void addItem(String item) {
        this.themes.add(0, item);
        notifyItemInserted(0);
    }


    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final String url = themes.get(position);
        Glide.with(context).load(url).into(holder.imageView);
        holder.imageView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.INTENT_IMAGE, url);
            context.startActivity(intent);
        });

    }


    public void removeItem(int position) {
        themes.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return themes.size();
    }

    public interface OnDataSelected {

        void onSelected(String model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;


        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);

        }
    }

}
