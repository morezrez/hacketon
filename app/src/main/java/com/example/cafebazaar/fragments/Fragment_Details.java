package com.example.cafebazaar.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafebazaar.SqliteOpenHelper;
import com.example.cafebazaar.Adapters.AppSlidesAdapter;
import com.example.cafebazaar.Adapters.CommentsAdapter;
import com.example.cafebazaar.R;
import com.example.cafebazaar.models.Model_App;
import com.example.cafebazaar.models.Model_CommentBody;
import com.example.cafebazaar.models.Model_MyComments;
import com.example.cafebazaar.retrofit.ApiClient;
import com.example.cafebazaar.retrofit.ApiService;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Details extends Fragment {

    View view;
    String bName, bIcon, bId, bKind;
    TextView txtAppName;
    TextView txtKind;
    RoundedImageView imgIcon;
    Model_App app;
    TextView txtDeveloperName;
    TextView txtOptionDownload;
    TextView txtOptionAveScore;
    TextView txtOptionCommentCount;
    TextView txtOptionCatName;
    TextView txtOptionSize;
    TextView txtDesc;
    ImageView img_cat;
    RecyclerView slidesRecycler;
    List<String> slides;
    RecyclerView commentsRecycler;
    TextView txtMore;
    AppCompatRatingBar ratingBar;
    SharedPreferences sharedPreferences;
    int star = 0;
    ImageView ic_share;
    ImageView ic_favorite;
    SqliteOpenHelper sqliteOpenHelper;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details, container, false);

        Bundle bundle = getArguments();
        bName = bundle.getString("name");
        bIcon = bundle.getString("icon");
        bId = bundle.getString("id");
        bKind = bundle.getString("kind");

        firstSetup();
        getUniqeAppFromServer();

        sqliteOpenHelper = new SqliteOpenHelper(getContext());
        Cursor cursor = sqliteOpenHelper.getInfo();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int appId = cursor.getInt(1);

            if (appId == Integer.parseInt(bId)) {
                ic_favorite.setColorFilter(Color.argb(255, 0, 96, 100));

            }
        }



        ic_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long result = sqliteOpenHelper.addAppToFavorite(Integer.parseInt(bId), bName, bIcon, bKind);
                Toast.makeText(getActivity(), "برنامه به علاقه مندی ها افزوده شد", Toast.LENGTH_SHORT).show();
                ic_favorite.setColorFilter(Color.argb(255, 0, 96, 100));
            }
        });

        ic_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://cafebazaar.ir/app/homeworkout.homeworkouts.noequipment/?l=fa");
                startActivity(Intent.createChooser(shareIntent, "انتشار اپلیکیشن " + bName));
            }
        });

        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sharedPreferences = getContext().getSharedPreferences("home", Context.MODE_PRIVATE);
                    final String userId = sharedPreferences.getString("userId", "");
                        final Dialog dialog = new Dialog(getContext());
                        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.comment_dialog);
                        final AppCompatRatingBar ratingDialog = dialog.findViewById(R.id.dialog_rating_bar);
                        TextView txtUserName = dialog.findViewById(R.id.txt_dialog_user_name);
                        Button btnSaveComment = dialog.findViewById(R.id.btn_dialog_save_comment);
                        final EditText edtCommentTitle = (EditText) dialog.findViewById(R.id.edt_dialog_comment_title);
                        txtUserName.setText("کاربر");
                        btnSaveComment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                star = Math.round(ratingDialog.getRating());
                                String commentTitle = edtCommentTitle.getText().toString();
                                String appId = app.getId();
                                if (commentTitle.equals("") || star==0){
                                    Toast.makeText(getContext(), "لطفا امتیاز و نظر خود را وارد کنید", Toast.LENGTH_SHORT).show();
                                }else {
                                    ApiService service = ApiClient.getClient().create(ApiService.class);
                                    Model_CommentBody commentBody = new Model_CommentBody();
                                    commentBody.setAppId(appId);
                                    commentBody.setUserId(userId);
                                    commentBody.setStar(star);
                                    commentBody.setCommentTitle(commentTitle);
                                    Call<ResponseBody> call = service.addComment(appId, userId, commentTitle, star);
                                    call.enqueue(new Callback<ResponseBody>() {
                                                     @Override
                                                     public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                         try {
                                                             String addResult = response.body().string();
                                                             addResult = addResult.trim();
                                                             if (addResult.equals("ok")) {
                                                                 Toast.makeText(getContext(), "نظر شما با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                                                 dialog.dismiss();
                                                             } else {
                                                                 Toast.makeText(getContext(), "لطفا اتصال خود به اینترنت را بررسی کنید", Toast.LENGTH_SHORT).show();
                                                             }
                                                         } catch (IOException e) {
                                                             e.printStackTrace();
                                                         }
                                                     }

                                                     @Override
                                                     public void onFailure(Call<ResponseBody> call, Throwable t) {

                                                     }
                                                 }
                                    );
                                }


                            }
                        });
                        dialog.show();

                }

                return true;
            }
        });


        return view;
    }


    public void firstSetup() {

        imgIcon = view.findViewById(R.id.img_app_icon);
        txtAppName = view.findViewById(R.id.txt_app_name);
        txtKind = view.findViewById(R.id.txt_kind);
        ratingBar = (AppCompatRatingBar) view.findViewById(R.id.fr_detail_rating_bar);
        ic_share=view.findViewById(R.id.ic_share);
        ic_favorite=view.findViewById(R.id.ic_favorite);


        Picasso.with(getContext()).load(bIcon).into(imgIcon);
        txtAppName.setText(bName);
        if (bKind.equals("free")) {
            txtKind.setText("رایگان");
        } else {
            txtKind.setText("+پرداخت درون برنامه ای");
        }

        slidesRecycler = view.findViewById(R.id.rc_app_slides);
        commentsRecycler = view.findViewById(R.id.rv_fragmentDetail_comments);

        slidesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        commentsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


    }


    public void getUniqeAppFromServer() {

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<Model_App> call = service.getUniqeApp(bId);
        txtDeveloperName = view.findViewById(R.id.txt_developer_name);
        txtOptionAveScore = view.findViewById(R.id.txt_score);
        txtOptionCatName = view.findViewById(R.id.txt_cat_name);
        txtOptionCommentCount = view.findViewById(R.id.txt_comment_count);
        txtOptionDownload = view.findViewById(R.id.txt_install_count);
        txtOptionSize = view.findViewById(R.id.txt_size);
        txtDesc = view.findViewById(R.id.txt_fr_detail_desc);
        img_cat = view.findViewById(R.id.img_cat);


        call.enqueue(new Callback<Model_App>() {
            @Override
            public void onResponse(Call<Model_App> call, Response<Model_App> response) {

                app = response.body();
                slides = app.getSlides();

                txtDeveloperName.setText(app.getUserName());
                txtOptionDownload.setText(app.getDownload());
                txtOptionAveScore.setText("2.3");
                txtOptionCatName.setText(app.getCatName());
                txtOptionCommentCount.setText("70 نفر");
                txtOptionSize.setText(app.getSize() + " " + "مگابایت");
                txtDesc.setText(app.getDecs());
                Picasso.with(getContext()).load(app.getCatIcon()).into(img_cat);

                txtMore = view.findViewById(R.id.txt_fr_detail_more);

                slidesRecycler.setAdapter(new AppSlidesAdapter(getContext(), slides));

                List<Model_MyComments> comments = app.getComments();
                commentsRecycler.setAdapter(new CommentsAdapter(getContext(), comments));

                txtMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Bundle bundle = new Bundle();
                        bundle.putString("desc", app.getDecs());
                        bundle.putString("app_name", app.getName());


                        FragmentManager manager = (Objects.requireNonNull(getActivity())).getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();

                        Fragment_Description description = new Fragment_Description();
                        description.setArguments(bundle);
                        transaction.replace(R.id.rel_main_parentAllView, description);
                        transaction.addToBackStack(null);
                        transaction.commit();


                    }
                });


            }

            @Override
            public void onFailure(Call<Model_App> call, Throwable t) {

            }
        });

    }


}
