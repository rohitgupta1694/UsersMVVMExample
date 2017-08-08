package com.rohit.usersmvvmexample.baseUiComponents.interfaces;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;

public interface MvvmViewModel<V extends MvvmView> extends Observable {
    void attachView(V view, Bundle savedInstanceState);

    void detachView();

    void saveInstanceState(@NonNull Bundle outState);
}
