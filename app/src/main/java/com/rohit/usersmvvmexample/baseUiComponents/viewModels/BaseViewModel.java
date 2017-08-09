package com.rohit.usersmvvmexample.baseUiComponents.viewModels;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.rohit.usersmvvmexample.baseUiComponents.interfaces.MvvmView;
import com.rohit.usersmvvmexample.baseUiComponents.interfaces.MvvmViewModel;

public abstract class BaseViewModel<V extends MvvmView> extends BaseObservable
        implements MvvmViewModel<V> {

    //region Variables

    private V mvvmView;

    //endregion

    //region Getter Methods

    public V getMvvmView() {
        return mvvmView;
    }

    public final boolean isViewAttached() {
        return mvvmView != null;
    }

    //endregion

    //region Implemented Methods

    @Override
    @CallSuper
    public void attachView(V view, Bundle savedInstanceState) {
        this.mvvmView = mvvmView;
    }

    @Override
    @CallSuper
    public void detachView() {
        mvvmView = null;
    }

    @Override
    @CallSuper
    public void saveInstanceState(@NonNull Bundle outState) {

    }

    //endregion

}
