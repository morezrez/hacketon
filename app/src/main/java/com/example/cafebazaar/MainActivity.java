package com.example.cafebazaar;

import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cafebazaar.fragments.Fragment_Apps;
import com.example.cafebazaar.fragments.Fragment_Cats;
import com.example.cafebazaar.fragments.Fragment_Home;
import com.example.cafebazaar.fragments.Fragment_Search;
import com.example.cafebazaar.fragments.Fragment_Tops;
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
                    case R.id.tab_home:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.rl_fragment_container, new Fragment_Home());
                        break;

                    case R.id.tab_tops:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.rl_fragment_container, new Fragment_Tops());
                        break;

                    case R.id.tab_cats:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.rl_fragment_container, new Fragment_Cats());
                        break;

                    case R.id.tab_search:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.rl_fragment_container, new Fragment_Search());
                        break;

                    case R.id.tab_apps:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.rl_fragment_container, new Fragment_Apps());
                        break;
                }
                transaction.commit();
            }
        });

    }
}
