package com.rohit.usersmvvmexample.viewmodel;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.rohit.usersmvvmexample.adapters.UsersListAdapter;
import com.rohit.usersmvvmexample.baseUiComponents.viewModels.BaseViewModel;
import com.rohit.usersmvvmexample.interfaces.UsersListView;
import com.rohit.usersmvvmexample.models.User;
import com.rohit.usersmvvmexample.models.UsersList;
import com.rohit.usersmvvmexample.usersApi.UsersAPI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import io.realm.RealmList;

public class UserListVM extends BaseViewModel<UsersListView> {

    //region Variables

    private Realm realm;
    public UsersListAdapter mAdapter;
    public LinearLayoutManager linearLayoutManager;
    private static final String TAG = UserListVM.class.getSimpleName();
    private PublishSubject<List<User>> usersListPublishSubject = PublishSubject.create();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    //endregion

    //region Constructor Methods

    public UserListVM(Context context, Realm realm) {
        this.realm = realm;
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mAdapter = new UsersListAdapter();
    }

    //endregion

    //region Override Methods

    @Override
    public void attachView(UsersListView view, Bundle savedInstanceState) {
        super.attachView(view, savedInstanceState);
        // TODO: 8/8/17 Add Like Change Observable
        /*compositeDisposable.add();*/

        compositeDisposable.add(usersListPublishSubject.doOnNext(users -> {
            List<UserItemVM> userItemVMs = new ArrayList<>();
            for (User user : users) {
                userItemVMs.add(new UserItemVM(user.getId(), realm));
            }
            if (mAdapter.getItemCount() > 0)
                mAdapter.appendData(userItemVMs);
            else
                mAdapter.setData(userItemVMs);
        }).subscribe());
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear();
    }

    //endregion

    //region API Calls

    public void loadData() {
        compositeDisposable.add(UsersAPI.getInstance().getUsersList()
                .doOnNext(this::storingDataToRealm)
                .doOnError(throwable -> Log.d(TAG, throwable.getCause().getMessage()))
                .subscribe());
    }

    //endregion

    //region Helper Methods

    private void storingDataToRealm(List<User> users) {
        //storing data to Realm
        realm.executeTransactionAsync(realm -> {
            realm.copyToRealmOrUpdate(users);
            UsersList usersList = realm.where(UsersList.class).findFirst();
            if (usersList != null) {
                RealmList<User> usersRealmList = usersList.getmUsersList();
                usersRealmList.addAll(realm.where(User.class).findAll());
                if (usersRealmList.size() > 0) {
                    Set<User> seen = new HashSet<>();
                    Iterator<User> it = usersList.getmUsersList().iterator();
                    while (it.hasNext()) {
                        User list = it.next();
                        if (seen.contains(list)) {
                            it.remove();
                        }
                        seen.add(list);
                    }
                    Log.d(TAG, usersRealmList.size() + "");
                }
            }
        }, () -> usersListPublishSubject.onNext(users), error -> {

        });
    }

    //endregion

}