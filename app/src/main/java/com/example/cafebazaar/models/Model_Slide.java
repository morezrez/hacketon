package com.example.cafebazaar.models;

import com.google.gson.annotations.SerializedName;

public class Model_Slide {

    @SerializedName("slide")
    private String img_url;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
