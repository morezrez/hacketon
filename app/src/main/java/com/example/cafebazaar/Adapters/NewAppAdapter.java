package com.example.cafebazaar.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cafebazaar.R;
import com.example.cafebazaar.fragments.Fragment_Details;
import com.example.cafebazaar.models.Model_App;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewAppAdapter extends RecyclerView.Adapter<NewAppAdapter.NewAppViewHolder> {

    Context context;
    List<Model_App> apps;

    public NewAppAdapter(Context context, List<Model_App> apps) {
        this.apps = apps;
        this.context = context;
    }

    @NonNull
    @Override
    public NewAppViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fr_home_new_app_rv_row,viewGroup,false);
        return new NewAppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewAppViewHolder newAppViewHolder, int i) {

        final Model_App application = apps.get(i);
        Picasso.with(context).load(application.getIcon()).into(newAppViewHolder.img);
        newAppViewHolder.txtTitle.setText(application.getName());
        if (application.getKind().equals("free")){
            newAppViewHolder.imgCoin.setVisibility(View.GONE);
        }

        newAppViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                bundle.putString("id" , application.getId());
                bundle.putString("kind" , application.getKind());
                bundle.putString("name" , application.getName());
                bundle.putString("icon" , application.getIcon());

                FragmentManager manager=((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();

                Fragment_Details details=new Fragment_Details();
                details.setArguments(bundle);
                transaction.replace(R.id.rel_main_parentAllView,details);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

    }

    @Override
    public int getItemCount() { return apps.size(); }

    public class NewAppViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parent;
        ImageView img;
        TextView txtTitle;
        ImageView imgCoin;

        public NewAppViewHolder(@NonNull View itemView) {
            super(itemView);

            parent=itemView.findViewById(R.id.rl_fragmentHome_newapp_row_parent);
            img=itemView.findViewById(R.id.imgNewAppRow);
            txtTitle=itemView.findViewById(R.id.txt_newApp_title);
            imgCoin=itemView.findViewById(R.id.img_newApp_coin);

        }
    }
}
