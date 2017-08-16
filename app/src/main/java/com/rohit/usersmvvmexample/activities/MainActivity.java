package com.rohit.usersmvvmexample.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jakewharton.rxbinding2.support.design.widget.RxTabLayout;
import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.adapters.ViewPagerAdapter;
import com.rohit.usersmvvmexample.fragments.LikedFragment;
import com.rohit.usersmvvmexample.fragments.UserListFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    //region Views - Inflated by ButterKnife

    @BindView(R.id.activity_main_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.activity_main_view_pager)
    ViewPager viewPager;

    //endregion

    //region Variables

    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private final CompositeDisposable compositeDisposables = new CompositeDisposable();

    //endregion

    //region Override Methods

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeViews();
    }

    @Override
    protected void onDestroy() {
        compositeDisposables.clear();
        super.onDestroy();
    }

    //endregion

    //region View Handling Methods

    private void initializeTabs() {
        mFragmentList.add(new UserListFragment());
        mFragmentList.add(new UserListFragment());
        mFragmentList.add(new LikedFragment());

        mTitles.add("Tab 1");
        mTitles.add("Tab 2");
        mTitles.add("Tab 3");
    }

    private void initializeViews() {
        initializeTabs();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList, mTitles);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);

        Observable<TabLayout.Tab> tabObservable = RxTabLayout.selections(tabLayout)
                .observeOn(AndroidSchedulers.mainThread())
                .share()
                .doOnNext(tab -> viewPager.setCurrentItem(tab.getPosition(), true));

        compositeDisposables.add(tabObservable.subscribe());
    }

    //endregion

}