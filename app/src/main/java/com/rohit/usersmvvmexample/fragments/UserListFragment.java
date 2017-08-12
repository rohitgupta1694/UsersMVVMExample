package com.rohit.usersmvvmexample.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.UsersMVVMApplication;
import com.rohit.usersmvvmexample.databinding.UsersListFragmentBinding;
import com.rohit.usersmvvmexample.interfaces.UsersListView;
import com.rohit.usersmvvmexample.viewmodel.UserListVM;

import javax.inject.Inject;

import io.realm.Realm;

public class UserListFragment extends Fragment implements UsersListView {

    //region Variables

    private UserListVM vm;
    private RecyclerView recyclerView;
    private static final String TAG = UserListFragment.class.getSimpleName();

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
        ((UsersMVVMApplication) getActivity().getApplication()).getObjectsComponent().inject(this);
        UsersListFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.users_list_fragment, container, false);
        vm = new UserListVM(getContext(), realm);
        binding.setVm(vm);
        recyclerView = binding.usersFragmentRecyclerView;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews();
        vm.attachView(this, savedInstanceState);
        vm.loadData();
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
        setScrollListener(recyclerView);
        recyclerView.setAdapter(vm.mAdapter);
    }

    private void setScrollListener(RecyclerView recyclerView) {
        RxRecyclerView.scrollStateChanges(recyclerView)
                .filter(integer -> vm.mAdapter.getItemCount() != 0 &&
                        integer == RecyclerView.SCROLL_STATE_IDLE)
                .map(integer -> (vm.mAdapter.getItemCount() - 1 ==
                        vm.linearLayoutManager.findLastVisibleItemPosition()))
                .doOnNext(aBoolean -> {
                    if (aBoolean)
                        vm.loadData();
                }).doOnError(throwable -> Log.d(TAG, throwable.getCause().getMessage())).subscribe();
    }

    @Override
    public void onRefresh(boolean success) {

    }

    //endregion

}
