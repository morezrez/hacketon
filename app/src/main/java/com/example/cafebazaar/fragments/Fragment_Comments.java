package com.example.cafebazaar.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cafebazaar.Adapters.FragmentCommentsAdapter;
import com.example.cafebazaar.R;
import com.example.cafebazaar.models.Model_MyComments;
import com.example.cafebazaar.retrofit.ApiClient;
import com.example.cafebazaar.retrofit.ApiService;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Comments extends Fragment {


    View view;
    TextView txtToolbar;
    RecyclerView commentsRecycler;
    List<Model_MyComments> comments;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_comments, container, false);

        //        ArrayList<Parcelable> bundle=getArguments().getParcelableArrayList("comments");
//        MyComment comment= (MyComment) bundle.get(0);
        Bundle bundle=getArguments();
        String appId=bundle.getString("id");
        setUpViews();
        getComments(appId);



        return view;
    }

    public void setUpViews(){
        commentsRecycler=view.findViewById(R.id.rv_fragmentComment_comments);
        txtToolbar=(TextView)view.findViewById(R.id.txt_toolbar);
        txtToolbar.setText("نظرات");

        commentsRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private void getComments(String appId) {
        ApiService service= ApiClient.getClient().create(ApiService.class);
        Call<List<Model_MyComments>> call=service.getAppComments(appId);
        call.enqueue(new Callback<List<Model_MyComments>>() {
            @Override
            public void onResponse(Call<List<Model_MyComments>> call, Response<List<Model_MyComments>> response) {

                comments=response.body();
                commentsRecycler.setAdapter(new FragmentCommentsAdapter(comments , getContext()));


            }

            @Override
            public void onFailure(Call<List<Model_MyComments>> call, Throwable t) {

            }
        });


    }


}
