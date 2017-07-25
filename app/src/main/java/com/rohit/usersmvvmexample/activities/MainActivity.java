package com.rohit.usersmvvmexample.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rohit.usersmvvmexample.UsersMVVMApplication;

import javax.inject.Inject;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Inject
    Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((UsersMVVMApplication) getApplication()).getObjectsComponent().inject(this);
    }

}