package com.example.cafebazaar.fragments.magazine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cafebazaar.Adapters.MagazineTabAllViewPagerAdapter;
import com.example.cafebazaar.Adapters.MagazineViewPagerAdapter;
import com.example.cafebazaar.R;

public class Fragment_Mag_All extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    View view;
    MagazineTabAllViewPagerAdapter magazineTabAllViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mag_all, container, false);
        setUpViews();
        return view;

    }

    private void setUpViews() {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_FragmentMag_all_magTabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.vp_fragmentmag_all_viewPager);
        tabLayout.setupWithViewPager(viewPager);
        magazineTabAllViewPagerAdapter = new MagazineTabAllViewPagerAdapter(getChildFragmentManager());
        magazineTabAllViewPagerAdapter.addFragment(new Fragment_Mag_Technology(), "تکنولوژی");
        magazineTabAllViewPagerAdapter.addFragment(new Fragment_Mag_FilmAndSeries(), "فیلم و سریال");
        magazineTabAllViewPagerAdapter.addFragment(new Fragment_Mag_Economy(), "اقتصاد");
        magazineTabAllViewPagerAdapter.addFragment(new Fragment_Mag_cooking(), "آشپزی");
        magazineTabAllViewPagerAdapter.addFragment(new Fragment_Mag_Car(), "خودرو");
        magazineTabAllViewPagerAdapter.addFragment(new Fragment_Mag_Apps(), "اپلیکیشن");
        magazineTabAllViewPagerAdapter.addFragment(new Fragment_Mag_Sport(), "ورزش");
        viewPager.setAdapter(magazineTabAllViewPagerAdapter);
        viewPager.setCurrentItem(6);
    }

}
