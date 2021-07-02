package com.example.cafebazaar.models;

import com.google.gson.annotations.SerializedName;

public class Model_Mag_Sport_Euro2020 {

    @SerializedName("id")
    private String id;

    @SerializedName("img_url")
    private String img_url;

    @SerializedName("text")
    private String text;

    @SerializedName("url")
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
