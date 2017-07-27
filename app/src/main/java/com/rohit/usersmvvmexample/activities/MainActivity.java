package com.rohit.usersmvvmexample.activities;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.adapters.ViewPagerAdapter;
import com.rohit.usersmvvmexample.databinding.ActivityMainBinding;
import com.rohit.usersmvvmexample.viewModels.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityViewModel mainActivityViewModel = new MainActivityViewModel(this);
        activityMainBinding.setVm(mainActivityViewModel);
    }

    @BindingAdapter("setTabData")
    public static void setTabData(CommonTabLayout tabLayout, ArrayList<CustomTabEntity> tabEntities) {
        tabLayout.setTabData(tabEntities);
    }

    @BindingAdapter("setOnTabSelectListener")
    public static void setObTabSelectListener(CommonTabLayout tabLayout, OnTabSelectListener onTabSelectListener) {
        tabLayout.setOnTabSelectListener(onTabSelectListener);
    }

    @BindingAdapter("setOffScreenPageLimit")
    public static void setOffscreenPageLimit(ViewPager viewPager, int offScreenPageLimit) {
        viewPager.setOffscreenPageLimit(offScreenPageLimit);
    }

    @BindingAdapter("setViewPagerAdapter")
    public static void setViewPagerAdapter(ViewPager viewPager, ViewPagerAdapter viewPagerAdapter) {
        viewPager.setAdapter(viewPagerAdapter);
    }

    @BindingAdapter("pager")
    public static void setupWithViewPager(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

}