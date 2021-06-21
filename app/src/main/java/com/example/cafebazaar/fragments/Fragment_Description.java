package com.example.cafebazaar.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cafebazaar.R;

import org.jetbrains.annotations.Nullable;

public class Fragment_Description extends Fragment {

    View view;
    TextView txtToolbar;
    TextView txtDesc;
    String appName;
    String desc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_description, container, false);

        txtToolbar = view.findViewById(R.id.txt_toolbar);
        txtDesc=view.findViewById(R.id.txt_fr_desc_desc);

        Bundle bundle = getArguments();
        appName = bundle.getString("app_name");
        desc = bundle.getString("desc");

        txtToolbar.setText("توضیحات" +" "+ appName);
        txtDesc.setText(desc);


        return view;
    }
}
