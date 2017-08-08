package com.rohit.usersmvvmexample.interfaces;

import com.rohit.usersmvvmexample.baseUiComponents.interfaces.MvvmView;

public interface UsersView extends MvvmView {
    void onRefresh(boolean success);
}
