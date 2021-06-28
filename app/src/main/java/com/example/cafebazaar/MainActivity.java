package com.example.cafebazaar;

import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cafebazaar.fragments.Fragment_Updates;
import com.example.cafebazaar.fragments.Fragment_Magazine;
import com.example.cafebazaar.fragments.Fragment_Games;
import com.example.cafebazaar.fragments.Fragment_Video;
import com.example.cafebazaar.fragments.Fragment_Apps;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

public class MainActivity extends AppCompatActivity {
    Typeface behdadTyapeface;
    BottomNavigation bottomNavigation;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

setUpViews();

    }

    public  void setUpViews(){


        //bottom navigation
        bottomNavigation = (BottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setDefaultItem(4);
        //set font for bottom navigation
        behdadTyapeface = Typeface.createFromAsset(getAssets(), "fonts/behdad.ttf");
        bottomNavigation.setTypeface(behdadTyapeface);

     bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int itemId) {
                switch (itemId) {
                    case R.id.tab_apps:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.rl_fragment_container, new Fragment_Apps());
                        break;

                    case R.id.tab_games:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.rl_fragment_container, new Fragment_Games());
                        break;

                    case R.id.tab_magazine:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.rl_fragment_container, new Fragment_Magazine());
                        break;

                    case R.id.tab_video:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.rl_fragment_container, new Fragment_Video());
                        break;

                    case R.id.tab_updates:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.rl_fragment_container, new Fragment_Updates());
                        break;
                }
                transaction.commit();
            }
        });

    }
}
