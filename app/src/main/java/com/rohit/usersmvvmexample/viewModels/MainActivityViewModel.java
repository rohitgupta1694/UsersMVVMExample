package com.rohit.usersmvvmexample.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.rohit.usersmvvmexample.adapters.ViewPagerAdapter;
import com.rohit.usersmvvmexample.fragments.UserListFragment;
import com.rohit.usersmvvmexample.models.TabEntity;

import java.util.ArrayList;

public class MainActivityViewModel extends BaseObservable {

    //region Variables

    private Context context;
    private int offScreenPageLimit;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<Fragment> fragmentsList = new ArrayList<>();
    private ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
    private OnTabSelectListener onTabSelectListener;

    //endregion

    //region Constructor Methods

    public MainActivityViewModel(Context context) {
        this.context = context;
        prepareAll();
    }

    //endregion

    //region Setter Methods

    private void setFragmentsList() {
        fragmentsList.add(new UserListFragment());
        fragmentsList.add(new UserListFragment());
        offScreenPageLimit = fragmentsList.size() + 1;
    }

    private void setTabEntities() {
        for (int i = 0; i < fragmentsList.size(); i++) {
            tabEntities.add(new TabEntity(String.valueOf(i + 1)));
        }
    }

    private void setOnTabSelectListener() {
        onTabSelectListener = new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        };
    }

    private void setViewPagerAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(((AppCompatActivity) context).getSupportFragmentManager(),
                fragmentsList);
    }

    private void prepareAll() {
        setFragmentsList();
        setTabEntities();
        setOnTabSelectListener();
        setViewPagerAdapter();
    }

    //endregion

    //region Getter Methods

    @Bindable
    public int getOffScreenPageLimit() {
        return offScreenPageLimit;
    }

    @Bindable
    public ViewPagerAdapter getViewPagerAdapter() {
        return viewPagerAdapter;
    }

    @Bindable
    public ArrayList<CustomTabEntity> getTabEntities() {
        return tabEntities;
    }

    @Bindable
    public OnTabSelectListener getOnTabSelectListener() {
        return onTabSelectListener;
    }

    //endregion

}
