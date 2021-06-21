package com.example.cafebazaar.models;

import com.google.gson.annotations.SerializedName;

public class Model_CommentBody {

    @SerializedName("app_id")
    private String appId;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("comment_title")
    private String commentTitle;
    @SerializedName("star")
    private int star;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
