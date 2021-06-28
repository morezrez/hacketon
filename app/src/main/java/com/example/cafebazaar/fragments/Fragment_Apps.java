package com.example.cafebazaar.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.cafebazaar.Adapters.BannersAdapter;
import com.example.cafebazaar.Adapters.NewAppAdapter;
import com.example.cafebazaar.R;
import com.example.cafebazaar.models.Model_App;
import com.example.cafebazaar.models.Model_Banner;
import com.example.cafebazaar.models.Model_Slider;
import com.example.cafebazaar.retrofit.ApiClient;
import com.example.cafebazaar.retrofit.ApiService;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Apps extends Fragment implements BaseSliderView.OnSliderClickListener {
    //slider
    ArrayList<String> sliderArray;
    private SliderLayout sliderLayout;
    List<Model_Slider> sliders;

    //banners
    List<String> bannersArray;
    List<Model_Banner> banners;
    RecyclerView bannerRecycler;

    //newApps
    RecyclerView newAppsRecycler;
    List<Model_App> newApps;

    //newUpdatedApps
    RecyclerView newUpdatedAppsRecycler;
    List<Model_App> newUpdatedApps;

    ImageView imgProfile;
    SharedPreferences sharedPreferences;
    String phoneNumber="";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apps, container, false);
        setupViews(view);
        setSlideForSlider();
        getBanners();
        getNewApps();
        getNewUpdatedApps();


        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getContext().getSharedPreferences("home", Context.MODE_PRIVATE);
                String userName = sharedPreferences.getString("userName", "");
                if (userName.equals("")) {
                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
                    View bottomSheetView = getLayoutInflater().inflate(R.layout.buttom_sheet_phone_number, null);
                    bottomSheetDialog.setContentView(bottomSheetView);
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
                    bottomSheetBehavior.setPeekHeight(

                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 500, getResources().getDisplayMetrics())

                    );

                    final EditText edtPhoneNumber =bottomSheetView.findViewById(R.id.edt_bottomSheet_phoneNumber);
                    Button btnRegister = bottomSheetView.findViewById(R.id.btn_bottomSheet_register);
                    final ProgressBar progressBar = bottomSheetView.findViewById(R.id.pb_bottomSheet_progressBar);

                    btnRegister.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (edtPhoneNumber.getText().toString().equals("")) {
                                edtPhoneNumber.setError("لطفا شماره همراه خود را وارد کنید");
                            } else {
                                progressBar.setVisibility(View.VISIBLE);
                                phoneNumber = edtPhoneNumber.getText().toString();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("phoneNumber", phoneNumber);
                                editor.apply();
                                ApiService service = ApiClient.getClient().create(ApiService.class);
                                Call<ResponseBody> call = service.sendNumber(edtPhoneNumber.getText().toString());
                                call.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(final Call<ResponseBody> call, Response<ResponseBody> response) {
                                        try {
                                            Log.i("LOG", "onResponse: " + response.body().string());
                                            bottomSheetDialog.dismiss();
                                            final BottomSheetDialog validationDialog = new BottomSheetDialog(getContext());
                                            View validationView = getLayoutInflater().inflate(R.layout.buttom_sheet_validation_code, null);
                                            validationDialog.setContentView(validationView);
                                            validationDialog.show();
                                            final EditText edtValidationCode =validationView.findViewById(R.id.edt_bottomSheetValidation_code);
                                            Button btnValidation = validationView.findViewById(R.id.btn_bottomSheetValidation_validation);
                                            btnValidation.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    if (edtPhoneNumber.getText().toString().equals("")) {
                                                        edtPhoneNumber.setError("کد فعالسازی را وارد کنید");
                                                    } else {
                                                        ApiService service1 = ApiClient.getClient().create(ApiService.class);
                                                        Call<ResponseBody> call1 = service1.sendValidationCode(edtValidationCode.getText().toString(), phoneNumber);
                                                        call1.enqueue(new Callback<ResponseBody>() {
                                                            @Override
                                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                                                try {
                                                                    String userId = response.body().string();
                                                                    Log.i("Server", userId);
                                                                    userId = userId.trim();
                                                                    if (!userId.equals("validation faild")) {
                                                                        Toast.makeText(getContext(), "به فادفوب خوش امدید", Toast.LENGTH_SHORT).show();
                                                                        validationDialog.dismiss();
                                                                        sharedPreferences = getContext().getSharedPreferences("home", Context.MODE_PRIVATE);
                                                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                        editor.putString("userName", phoneNumber);
                                                                        editor.putString("userId", userId);
                                                                        editor.apply();

                                                                    } else {
                                                                        Toast.makeText(getContext(), "کد فعالسازی اشتباه است دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                                            }
                                                        });
                                                    }

                                                }
                                            });


                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    }
                                });

                            }

                        }
                    });

                    bottomSheetDialog.show();
                } else {

                    Bundle bundle = new Bundle();
                    String sharedPhoneNumber = sharedPreferences.getString("phoneNumber", phoneNumber);
                    bundle.putString("userName", sharedPhoneNumber);
                    FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    Fragment_Account fragmentAccount = new Fragment_Account();
                    fragmentAccount.setArguments(bundle);
                    transaction.replace(R.id.rel_main_parentAllView, fragmentAccount);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            }
        });
        return view;



    }


    private void setupViews(View view) {

        //slider
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        sliderArray = new ArrayList<>();
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);//for animation
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        //banner
        bannerRecycler = (RecyclerView) view.findViewById(R.id.rv_fragmentHome_banners);
        bannerRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //newApps
        newAppsRecycler = view.findViewById(R.id.rv_fragmentHome_newapp);
        newAppsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //newUpdatedApps
        newUpdatedAppsRecycler = view.findViewById(R.id.rv_fragmentHome_newupdated);
        newUpdatedAppsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        imgProfile = view.findViewById(R.id.imgProfile);


    }


    public void setSlideForSlider() {

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<List<Model_Slider>> call = service.getSliders();

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
                            setOnSliderClickListener(Fragment_Apps.this);

                    defaultSliderView.bundle(new Bundle());
                    sliderLayout.addSlider(defaultSliderView);
                }


            }

            @Override
            public void onFailure(Call<List<Model_Slider>> call, Throwable t) {

                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void getBanners() {


        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<List<Model_Banner>> call = service.getBanners();

        call.enqueue(new Callback<List<Model_Banner>>() {
            @Override
            public void onResponse(Call<List<Model_Banner>> call, Response<List<Model_Banner>> response) {

                banners = response.body();

                bannerRecycler.setAdapter(new BannersAdapter(banners, getContext()));

            }

            @Override
            public void onFailure(Call<List<Model_Banner>> call, Throwable t) {

                Toast.makeText(getContext(), t.toString() + "", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void getNewApps() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<List<Model_App>> call = service.getNewApps();

        call.enqueue(new Callback<List<Model_App>>() {
            @Override
            public void onResponse(Call<List<Model_App>> call, Response<List<Model_App>> response) {
                newApps = response.body();
                newAppsRecycler.setAdapter(new NewAppAdapter(getContext(), newApps));
            }

            @Override
            public void onFailure(Call<List<Model_App>> call, Throwable t) {

                Toast.makeText(getContext(), t + "", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getNewUpdatedApps() {

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<List<Model_App>> call = service.getNewUpdatedApps();

        call.enqueue(new Callback<List<Model_App>>() {
            @Override
            public void onResponse(Call<List<Model_App>> call, Response<List<Model_App>> response) {

                newUpdatedApps = response.body();
                newUpdatedAppsRecycler.setAdapter(new NewAppAdapter(getContext(), newUpdatedApps));
            }

            @Override
            public void onFailure(Call<List<Model_App>> call, Throwable t) {

                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(getContext(), "clicked!", Toast.LENGTH_SHORT).show();

    }
}

