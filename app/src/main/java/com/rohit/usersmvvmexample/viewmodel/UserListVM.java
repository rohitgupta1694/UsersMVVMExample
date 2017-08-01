package com.rohit.usersmvvmexample.viewmodel;

import android.util.Log;

import com.rohit.usersmvvmexample.models.User;
import com.rohit.usersmvvmexample.usersApi.UsersAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class UserListVM {

    private static final String TAG = UserListVM.class.getSimpleName();
    public PublishSubject<List<UserItemVM>> itemVMList = PublishSubject.create();

    public UserListVM() {
        loadData();
    }

    public void loadData() {
        List<UserItemVM> userItemVMs = new ArrayList<>();
        UsersAPI.getInstance().getUsersList()
                .doOnNext(usersList -> {
                    for (User user : usersList.getResponse()) {
                        userItemVMs.add(new UserItemVM(user));
                    }
                    itemVMList.onNext(userItemVMs);
                })
                .doOnError(throwable -> Log.d(TAG, throwable.getCause().getMessage()))
                .subscribe();
    }
}