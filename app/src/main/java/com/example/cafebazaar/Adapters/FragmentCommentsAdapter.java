package com.example.cafebazaar.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafebazaar.R;
import com.example.cafebazaar.models.Model_App;
import com.example.cafebazaar.models.Model_MyComments;
import com.example.cafebazaar.retrofit.ApiClient;
import com.example.cafebazaar.retrofit.ApiService;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCommentsAdapter extends RecyclerView.Adapter<FragmentCommentsAdapter.CommentsViewHolder> {

    List<Model_MyComments> comments;
    Context context;
    SharedPreferences sharedPreferences;
    String userId;

    public FragmentCommentsAdapter(List<Model_MyComments> comments, Context context) {
        this.comments = comments;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("home", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", "");
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.comments_row_detail, viewGroup, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentsViewHolder commentsViewHolder, int i) {

        final Model_MyComments comment = comments.get(i);
        commentsViewHolder.txtUserName.setText(comment.getUserName());
        commentsViewHolder.ratingBar.setRating(Float.parseFloat(comment.getStar()));
        commentsViewHolder.txtCommentTitle.setText(comment.getTitle());
        commentsViewHolder.txtLikeCount.setText(comment.getLike());
        commentsViewHolder.txtDislikeCount.setText(comment.getDislike());


        commentsViewHolder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApiService service = ApiClient.getClient().create(ApiService.class);
                Call<ResponseBody> call = service.setVote("like", userId, comment.getId());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String like = response.body().string();
                            like = like.trim();

                            if (!like.equals("شما قبلا به این نظر رای داده اید")) {
                                int likeCount = Integer.parseInt(commentsViewHolder.txtLikeCount.getText().toString());
                                commentsViewHolder.txtLikeCount.setText(likeCount + 1 + "");
                            }
                            Toast.makeText(context, like + "", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });

        commentsViewHolder.imgDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService service = ApiClient.getClient().create(ApiService.class);
                Call<ResponseBody> call = service.setVote("dislike", userId, comment.getId());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String like = response.body().string();
                            like = like.trim();
                            if (!like.equals("شما قبلا به این نظر رای داده اید")) {
                                int likeCount = Integer.parseInt(commentsViewHolder.txtDislikeCount.getText().toString());
                                commentsViewHolder.txtDislikeCount.setText(likeCount + 1 + "");
                            }
                            Toast.makeText(context, like + "", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {


        ImageView imgLike;
        ImageView imgDislike;
        TextView txtLikeCount;
        TextView txtDislikeCount;
        TextView txtUserName;
        TextView txtCommentTitle;
        AppCompatRatingBar ratingBar;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDislike = itemView.findViewById(R.id.img_commentDetailRow_dislike);
            imgLike = itemView.findViewById(R.id.img_commentDetailRow_like);
            txtLikeCount = itemView.findViewById(R.id.txt_commentDetailRow_likeCount);
            txtDislikeCount = itemView.findViewById(R.id.txt_commentDetailRow_dislikeCount);
            txtUserName = itemView.findViewById(R.id.txt_commentDetailRow_userName);
            txtCommentTitle = itemView.findViewById(R.id.txt_commentDetailRow_desc);
            ratingBar = itemView.findViewById(R.id.rt_commentDetailRow_ratingBar);

        }

    }
}

