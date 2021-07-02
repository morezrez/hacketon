package com.example.cafebazaar.fragments.magazine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.cafebazaar.Adapters.Magazine_Sport_Euro2020_RV_Adapter;
import com.example.cafebazaar.R;
import com.example.cafebazaar.fragments.Fragment_Apps;
import com.example.cafebazaar.models.Model_Banner;
import com.example.cafebazaar.models.Model_Mag_Sport_Euro2020;
import com.example.cafebazaar.models.Model_Slider;
import com.example.cafebazaar.retrofit.ApiClient;
import com.example.cafebazaar.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Mag_Sport extends Fragment implements BaseSliderView.OnSliderClickListener {

    View view;

    //slider
    SliderLayout newsPaperSliderLayout;
    List<Model_Slider> sliders;
    ArrayList<String> sliderArray;

    //euro2020 recycler
    List<Model_Mag_Sport_Euro2020> euroList;
    RecyclerView euroRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mag_sport, container, false);
        setUpViews(view);
        setSlideForSlider();
        setEuroRecycler();
        return view;

    }

    private void setUpViews(View view) {

        //slider
        newsPaperSliderLayout = view.findViewById(R.id.sportSlider);
        sliderArray = new ArrayList<>();
        newsPaperSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);//for animation
        newsPaperSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        //euro2020 recycler
        euroRecycler=view.findViewById(R.id.rv_mag_sport_euro2020);
        euroRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

    }

    public void setSlideForSlider() {

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<List<Model_Slider>> call = service.getSportSliders();
        call.enqueue(new Callback<List<Model_Slider>>() {
            @Override
            public void onResponse(Call<List<Model_Slider>> call, Response<List<Model_Slider>> response) {

                sliders = response.body();
                for (int i = 0; i < sliders.size(); i++) {

                    sliderArray.add(sliders.get(i).getUrl());

                }

                for (int i = 0; i < sliderArray.size(); i++) {

                    DefaultSliderView defaultSliderView = new DefaultSliderView(getContext());
                    defaultSliderView.
                            image(sliderArray.get(i)).
                            setScaleType(BaseSliderView.ScaleType.Fit).
                            setOnSliderClickListener(Fragment_Mag_Sport.this);

                    defaultSliderView.bundle(new Bundle());
                    newsPaperSliderLayout.addSlider(defaultSliderView);
                }

            }

            @Override
            public void onFailure(Call<List<Model_Slider>> call, Throwable t) {

            }
        });

    }

    public void setEuroRecycler(){
        ApiService service=ApiClient.getClient().create(ApiService.class);
        Call<List<Model_Mag_Sport_Euro2020>> call=service.getSportMagRVEuro2020();

        call.enqueue(new Callback<List<Model_Mag_Sport_Euro2020>>() {
            @Override
            public void onResponse(Call<List<Model_Mag_Sport_Euro2020>> call, Response<List<Model_Mag_Sport_Euro2020>> response) {
                euroList=response.body();
                euroRecycler.setAdapter(new Magazine_Sport_Euro2020_RV_Adapter(euroList,getContext()));
            }

            @Override
            public void onFailure(Call<List<Model_Mag_Sport_Euro2020>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
