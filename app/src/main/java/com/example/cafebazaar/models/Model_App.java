package com.example.cafebazaar.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_App {

    @SerializedName("id")
    private String id;
    @SerializedName("package_name")
    private String packageName;
    @SerializedName("name")
    private String name;
    @SerializedName("desc")
    private String decs;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("kind")
    private String kind;
    @SerializedName("type")
    private String type;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("cat_name")
    private String catName;
    @SerializedName("slide")
    private List<String> slides;
    @SerializedName("comments")
    private List<Model_MyComments> comments;

    public List<Model_MyComments> getComments() {
        return comments;
    }

    public void setComments(List<Model_MyComments> comments) {
        this.comments = comments;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<String> getSlides() {
        return slides;
    }

    public void setSlides(List<String> slides) {
        this.slides = slides;
    }

    public String getCatIcon() {
        return catIcon;
    }

    public void setCatIcon(String catIcon) {
        this.catIcon = catIcon;
    }

    @SerializedName("cat_icon")
    private String catIcon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImgSlide() {
        return imgSlide;
    }

    public void setImgSlide(String imgSlide) {
        this.imgSlide = imgSlide;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    @SerializedName("cat_id")
    private String catId;
    @SerializedName("size")
    private String size;
    @SerializedName("img_slide")
    private String imgSlide;
    @SerializedName("version")
    private String version;
    @SerializedName("icon")
    private String icon;
    @SerializedName("downloads")
    private String download;

}
