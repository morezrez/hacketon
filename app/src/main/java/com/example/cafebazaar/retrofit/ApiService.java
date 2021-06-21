package com.example.cafebazaar.retrofit;

import com.example.cafebazaar.models.Model_App;
import com.example.cafebazaar.models.Model_Banner;
import com.example.cafebazaar.models.Model_MyComments;
import com.example.cafebazaar.models.Model_Slider;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {



    @GET("getsliders.php")
    Call<List<Model_Slider>> getSliders();

    @GET("getbanners.php")
    Call<List<Model_Banner>> getBanners();

    @GET("getnewapp.php")
    Call<List<Model_App>> getNewApps();

    @GET("get_new_updated_apps.php")
    Call<List<Model_App>> getNewUpdatedApps();

    @GET("getuniqeapp.php")
    Call<Model_App> getUniqeApp(@Query("id") String id);

    @FormUrlEncoded
    @POST("sendsms.php")
    Call<ResponseBody> sendNumber(@Field("to") String phoneNumber);

    @FormUrlEncoded
    @POST("validation.php")
    Call<ResponseBody> sendValidationCode(@Field("code") String validationCode,@Field("phone_number") String number);


    @FormUrlEncoded
    @POST("rating.php")
    Call<ResponseBody> addComment(@Field("app_id") String appId , @Field("user_id") String userId , @Field("comment_title") String comment , @Field("star") int star);


    @FormUrlEncoded
    @POST("getAllComments.php")
    Call<List<Model_MyComments>> getAppComments(@Field("id") String appId );


    @GET("likedislike.php")
    Call<ResponseBody> setVote(@Query("vote") String vote,@Query("user_id") String userId,@Query("comment_id")String commentId);

}
