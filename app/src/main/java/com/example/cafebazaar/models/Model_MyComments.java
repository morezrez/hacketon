package com.example.cafebazaar.models;

import com.google.gson.annotations.SerializedName;

public class Model_MyComments {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("star")
    private String star;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("app_id")
    private String appId;
    @SerializedName("like")
    private String like;
    @SerializedName("dislike")
    private String dislike;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("app_name")
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
