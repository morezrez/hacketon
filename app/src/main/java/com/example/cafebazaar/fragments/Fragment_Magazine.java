package com.example.cafebazaar.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cafebazaar.Adapters.MagazineViewPagerAdapter;
import com.example.cafebazaar.R;

public class Fragment_Magazine extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    View view;
    MagazineViewPagerAdapter magazineViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_magazine,container,false);

        setUpviews();

        return  view;

    }

    private void setUpviews() {
        tabLayout=(TabLayout)view.findViewById(R.id.tab_FragmentMag_magTabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.vp_fragmentmag_viewPager);
        tabLayout.setupWithViewPager(viewPager);
        magazineViewPagerAdapter =new MagazineViewPagerAdapter(getChildFragmentManager());
        magazineViewPagerAdapter.addFragment(new Fragment_Mag_Me(),"علاقه‌مندی‌ها");
        magazineViewPagerAdapter.addFragment(new Fragment_Mag_All(),"همه");
        viewPager.setAdapter(magazineViewPagerAdapter);
        viewPager.setCurrentItem(1);
    }

}
