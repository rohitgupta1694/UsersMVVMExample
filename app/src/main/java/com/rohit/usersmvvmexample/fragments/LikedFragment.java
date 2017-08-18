package com.rohit.usersmvvmexample.fragments;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.UsersMVVMApplication;
import com.rohit.usersmvvmexample.databinding.UsersListFragmentBinding;
import com.rohit.usersmvvmexample.interfaces.UsersListView;
import com.rohit.usersmvvmexample.viewmodel.LikeListVM;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;

public class LikedFragment extends Fragment implements UsersListView {

    //region Variables

    private LikeListVM vm;
    private RecyclerView recyclerView;
    private Button likesListButton;

    //endregion

    //region Injected Dependencies

    @Inject
    Realm realm;

    //endregion

    //region Constructor Methods

    public LikedFragment() {
        // Required empty public constructor
    }

    //endregion

    //region Override Methods

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((UsersMVVMApplication) getActivity().getApplication()).getObjectsComponent().inject(this);
        UsersListFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.users_list_fragment, container, false);
        vm = new LikeListVM(getContext(), realm);
        binding.setVm(vm);
        recyclerView = binding.usersFragmentRecyclerView;
        likesListButton = binding.usersFragmentFetchLikedUsersButton;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews();
        vm.attachView(this, savedInstanceState);
        vm.searchForLikedUsers();

        RxView.clicks(likesListButton)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(o -> vm.searchForLikedUsers())
                .subscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        vm.detachView();
    }

    //endregion

    //region View Handling Methods

    private void initializeViews() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(vm.linearLayoutManager);
        recyclerView.setAdapter(vm.mAdapter);
    }

    //region Implemented Methods

    @Override
    public void onRefresh(boolean success) {

    }

    //endregion

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

}
