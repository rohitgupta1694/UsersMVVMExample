package com.rohit.usersmvvmexample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.UsersMVVMApplication;

import javax.inject.Inject;

import io.realm.Realm;

public class UserListFragment extends Fragment {

    //region Variables


    //endregion

    //region Injected Dependencies

    @Inject
    Realm realm;

    //endregion

    //region Constructor Methods

    public UserListFragment() {
        // Required empty public constructor
    }

    //endregion

    //region Override Methods

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.users_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((UsersMVVMApplication) getContext()).getObjectsComponent().inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //endregion

}
