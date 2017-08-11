package com.rohit.usersmvvmexample.baseUiComponents.interfaces;

import android.view.View;

import com.rohit.usersmvvmexample.models.User;

public interface UsersInterfaceViewModel<V extends MvvmView> extends MvvmViewModel<V> {

    void onLikeButtonCLicked(View view);

    void update(User user, boolean isLast);

}
