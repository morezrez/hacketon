package com.example.cafebazaar.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cafebazaar.R;
import com.example.cafebazaar.fragments.Fragment_Comments;
import com.example.cafebazaar.models.Model_MyComments;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    Context context;
    List<Model_MyComments> comments;

    public CommentsAdapter(Context context , List<Model_MyComments> comments) {
        this.comments=comments;
        this.context=context;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.fr_details_comments_row,viewGroup,false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder commentsViewHolder, int i) {

        final Model_MyComments myComment = comments.get(i);
        commentsViewHolder.ratingBar.setRating(Float.parseFloat(myComment.getStar()));
        commentsViewHolder.txtUserName.setText(myComment.getUserName());
        commentsViewHolder.txtComment.setText(myComment.getTitle());

        commentsViewHolder.commentParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                Bundle bundle=new Bundle();
                bundle.putString("id",myComment.getAppId());
                bundle.putString("app_name",myComment.getAppName());
//                bundle.putParcelableArrayList("comments", (ArrayList<? extends Parcelable>) comments);
                Fragment_Comments fragmentComments=new Fragment_Comments();
                fragmentComments.setArguments(bundle);
                transaction.replace(R.id.rel_main_parentAllView,fragmentComments);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });


    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder{
        TextView txtUserName;
        TextView txtComment;
        AppCompatRatingBar ratingBar;
        LinearLayout commentParent;
        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUserName=itemView.findViewById(R.id.txt_fr_detail_comment_user_name);
            txtComment=itemView.findViewById(R.id.txt_fr_detail_comment);
            ratingBar=itemView.findViewById(R.id.fr_detail_comment_rating_bar);
            commentParent=itemView.findViewById(R.id.comment_parent);

        }
    }
}
