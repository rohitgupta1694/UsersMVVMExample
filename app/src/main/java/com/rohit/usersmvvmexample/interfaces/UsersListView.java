package com.rohit.usersmvvmexample.interfaces;

import com.rohit.usersmvvmexample.baseUiComponents.interfaces.MvvmView;

public interface UsersListView extends MvvmView {
    void onRefresh(boolean success);
}
