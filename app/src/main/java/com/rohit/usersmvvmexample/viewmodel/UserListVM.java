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
    private PublishSubject<List<UserItemVM>> itemVMList = PublishSubject.create();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    //endregion

    //region Constructor Methods

    public UserListVM(Context context, Realm realm) {
        this.realm = realm;
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mAdapter = new UsersListAdapter(realm.where(User.class).findAll());
    }

    //endregion

    //region Override Methods

    @Override
    public void attachView(UsersListView view, Bundle savedInstanceState) {
        super.attachView(view, savedInstanceState);
        // TODO: 8/8/17 Add Like Change Observable
        /*compositeDisposable.add();*/

        compositeDisposable.add(itemVMList.doOnNext(userItemVMs -> {
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
        List<UserItemVM> userItemVMs = new ArrayList<>();
        compositeDisposable.add(UsersAPI.getInstance().getUsersList()
                .doOnNext(users -> {
                    storingDataToRealm(users);
                    for (User user : users) {
                        userItemVMs.add(new UserItemVM(user));
                    }
                    itemVMList.onNext(userItemVMs);
                })
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

                    // The default list is being added on all devices, so according to the merge rules the default list might
                    // be added multiple times. This is just a temporary fix. Proper ordered sets are being tracked here:
                    // https://github.com/realm/realm-core/issues/1206
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
        });
    }

    //endregion

}