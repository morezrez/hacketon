package com.example.cafebazaar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cafebazaar.R;
import com.example.cafebazaar.models.Model_Banner;
import com.example.cafebazaar.models.Model_Mag_Sport_Euro2020;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Magazine_Sport_Euro2020_RV_Adapter extends RecyclerView.Adapter<Magazine_Sport_Euro2020_RV_Adapter.SportViewHolder> {


    List<Model_Mag_Sport_Euro2020> banners;
    Context context;

    public Magazine_Sport_Euro2020_RV_Adapter(List<Model_Mag_Sport_Euro2020> banners , Context context) {
        this.banners = banners;
        this.context=context;
    }

    @NonNull
    @NotNull
    @Override
    public SportViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(context).inflate(R.layout.fr_mag_sport_rv_euro2020,viewGroup,false);
       return new SportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SportViewHolder sportViewHolder, int i) {
        Model_Mag_Sport_Euro2020 model_mag_sport_euro2020=banners.get(i);
        Picasso.with(context).load(model_mag_sport_euro2020.getImg_url()).into(sportViewHolder.imgEuro2020);
        sportViewHolder.txtEuro2020.setText(model_mag_sport_euro2020.getText());
    }

    @Override
    public int getItemCount() { return banners.size(); }

    public class SportViewHolder extends RecyclerView.ViewHolder{

        ImageView imgEuro2020;
        TextView txtEuro2020;

        public SportViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgEuro2020=itemView.findViewById(R.id.rv_mag_sport_euro2020_imgview);
            txtEuro2020=itemView.findViewById(R.id.txt_fr_sport_rv_euro2020);
        }
    }
}
