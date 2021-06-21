package com.example.cafebazaar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cafebazaar.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AppSlidesAdapter extends RecyclerView.Adapter<AppSlidesAdapter.SlidesViewHolder> {

    Context context;
    List<String> slides;

    public AppSlidesAdapter(Context context , List<String> slides) {
        this.context=context;
        this.slides=slides;
    }

    @NonNull
    @Override
    public SlidesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(context).inflate(R.layout.fr_details_app_slide_rv_row , viewGroup , false);
        return new AppSlidesAdapter.SlidesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlidesViewHolder slidesViewHolder, int i) {

        String imgSlide = slides.get(i);
        Picasso.with(context).load(imgSlide).into(slidesViewHolder.img_app_slide);

    }

    @Override
    public int getItemCount() {
        return slides.size();
    }

    public class SlidesViewHolder extends RecyclerView.ViewHolder{
        ImageView img_app_slide;

        public SlidesViewHolder(@NonNull View itemView) {
            super(itemView);
            img_app_slide=itemView.findViewById(R.id.img_app_slide);
        }
    }
}
