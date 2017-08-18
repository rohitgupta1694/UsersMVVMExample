package com.rohit.usersmvvmexample.viewmodel;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.rohit.usersmvvmexample.adapters.UsersListAdapter;
import com.rohit.usersmvvmexample.baseUiComponents.viewModels.BaseViewModel;
import com.rohit.usersmvvmexample.interfaces.UsersListView;
import com.rohit.usersmvvmexample.models.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;

public class LikeListVM extends BaseViewModel<UsersListView> {

    //region Variables

    private Realm realm;
    public UsersListAdapter mAdapter;
    public LinearLayoutManager linearLayoutManager;
    private List<User> likedUsersList = new ArrayList<>();
    private PublishSubject<List<User>> likesPublishSubject = PublishSubject.create();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    //endregion

    //region Constructor Methods

    public LikeListVM(Context context, Realm realm) {
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

        compositeDisposable.add(likesPublishSubject.doOnNext(users -> {
            List<UserItemVM> userItemVMs = new ArrayList<>();
            for (User user : users) {
                userItemVMs.add(new UserItemVM(user.getId(), realm));
            }
            mAdapter.setData(userItemVMs);
        }).subscribe());
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear();
    }

    //endregion

    //Query Users Table

    public void searchForLikedUsers() {
        realm.executeTransaction(realm1 -> {
            likedUsersList.addAll(realm1.where(User.class).equalTo("mLiked", true).findAll());
            Observable.just(likedUsersList.size())
                    .filter(integer -> integer > 0)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(integer -> {
                        setEmptyScreenVisible(View.GONE);
                        likesPublishSubject.onNext(likedUsersList);
                    }).subscribe();
            Observable.just(likedUsersList.size())
                    .filter(integer -> integer <= 0)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(integer -> setEmptyScreenVisible(View.VISIBLE)
                    ).subscribe();
        });
    }

}
