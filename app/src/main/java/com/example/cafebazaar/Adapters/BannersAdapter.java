package com.example.cafebazaar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cafebazaar.R;
import com.example.cafebazaar.models.Model_Banner;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannersAdapter extends RecyclerView.Adapter<BannersAdapter.BannerViewHolder> {

    List<Model_Banner> banners;
    Context context;

    public BannersAdapter(List<Model_Banner> banners , Context context) {
        this.banners = banners;
        this.context=context;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.fr_home_banner_rv_row , viewGroup , false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder bannerViewHolder, int i) {

        Model_Banner model_banner = banners.get(i);
        Picasso.with(context).load(model_banner.getUrl()).into(bannerViewHolder.img_rv_row);


        bannerViewHolder.linearLayout_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "banner clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder{

        RoundedImageView img_rv_row;
        LinearLayout linearLayout_parent;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            img_rv_row=(RoundedImageView)itemView.findViewById(R.id.img_bannerrow);
            linearLayout_parent=(LinearLayout)itemView.findViewById(R.id.linear_bannerrow_parent);
        }
    }
}
