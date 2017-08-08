package com.rohit.usersmvvmexample.viewmodel;

import android.os.Bundle;
import android.util.Log;

import com.rohit.usersmvvmexample.adapters.UsersListAdapter;
import com.rohit.usersmvvmexample.baseUiComponents.viewModels.BaseViewModel;
import com.rohit.usersmvvmexample.interfaces.UsersView;
import com.rohit.usersmvvmexample.models.User;
import com.rohit.usersmvvmexample.usersApi.UsersAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

public class UserListVM extends BaseViewModel<UsersView> {

    //region Variables

    public UsersListAdapter mAdapter;
    private static final String TAG = UserListVM.class.getSimpleName();
    public PublishSubject<List<User>> usersVMList = PublishSubject.create();
    public PublishSubject<List<UserItemVM>> itemVMList = PublishSubject.create();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    //endregion

    //region Constructor Methods

    public UserListVM() {
        loadData();
    }

    //endregion

    //region Override Methods

    @Override
    public void attachView(UsersView view, Bundle savedInstanceState) {
        super.attachView(view, savedInstanceState);
        // TODO: 8/8/17 Add Like Change Observable
        /*compositeDisposable.add();*/

        itemVMList.doOnNext(userItemVMs -> {
            if (mAdapter.getItemCount() > 0)
                mAdapter.appendData(userItemVMs);
            else
                mAdapter.setData(userItemVMs);
        });
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear();
    }

    //endregion

    //region API Calls

    public void loadData() {
        List<UserItemVM> userItemVMs = new ArrayList<>();
        compositeDisposable.add(UsersAPI.getInstance().getUsersList()
                .doOnNext(usersList -> {
                    for (User user : usersList.getResponse()) {
                        userItemVMs.add(new UserItemVM(user));
                    }
                    itemVMList.onNext(userItemVMs);
                    usersVMList.onNext(usersList.getResponse());
                })
                .doOnError(throwable -> Log.d(TAG, throwable.getCause().getMessage()))
                .subscribe());
    }

    //endregion

}