package com.example.cafebazaar.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafebazaar.R;



public class Fragment_Account extends Fragment {

    Bundle bundle;
    TextView txtUserName;
    View view;
    TextView txtExit;
    SharedPreferences sharedPreferences;
    String userName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_account,container,false);
        bundle=getArguments();
        userName=bundle.getString("userName");
        setUpView();
        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences=getContext().getSharedPreferences("home", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("userName","");
                editor.putString("userId","");
                editor.apply();
                FragmentManager fragmentManager= ((AppCompatActivity)getContext()).getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.rel_main_parentAllView,new Fragment_Home());
                transaction.commit();
                Toast.makeText(getActivity(), "شما از حساب کاربری خود خارج شدید", Toast.LENGTH_SHORT).show();

            }
        });


        return  view;

    }

    private void setUpView() {
        txtUserName=(TextView)view.findViewById(R.id.txt_fragmentAccount_userName);
        txtExit=(TextView)view.findViewById(R.id.txt_fragmentAccount_exit);
        txtUserName.setText(userName);

    }
}
