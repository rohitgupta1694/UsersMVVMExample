package com.rohit.usersmvvmexample.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    //region Variables

    private ArrayList<String> mTitles;
    private ArrayList<Fragment> fragments;

    //endregion

    //region Constructors

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments) {
        super(fm);
        fragments = mFragments;
    }

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, ArrayList<String> mTitles) {
        super(fm);
        fragments = mFragments;
        this.mTitles = mTitles;
    }

    //endregion

    //region Override Methods

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    //endregion
}
