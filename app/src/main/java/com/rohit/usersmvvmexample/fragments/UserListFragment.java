package com.rohit.usersmvvmexample.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.UsersMVVMApplication;
import com.rohit.usersmvvmexample.adapters.UsersListAdapter;
import com.rohit.usersmvvmexample.databinding.UsersListFragmentBinding;
import com.rohit.usersmvvmexample.viewmodel.UserItemVM;
import com.rohit.usersmvvmexample.viewmodel.UserListVM;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class UserListFragment extends Fragment {
    private static final String TAG = UserListFragment.class.getSimpleName();

    //region Variables

    private UserListVM vm;
    private RecyclerView recyclerView;
    private UsersListAdapter mAdapter;
    private List<UserItemVM> vmList = new ArrayList<>();

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
        UsersListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.users_list_fragment, container, false);
        recyclerView = binding.usersFragmentRecyclerView;

        vm = new UserListVM();
        setupRecyclerView();
        fetchUsers();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((UsersMVVMApplication) getActivity().getApplication()).getObjectsComponent().inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //endregion

    //region View Handling Methods

    private void fetchUsers() {
        vm.itemVMList.doOnNext(userItemVMs -> {
            if (mAdapter.getItemCount() > 0)
                mAdapter.appendData(userItemVMs);
            else
                mAdapter.setData(userItemVMs);
        }).subscribe();
    }

    private void setupRecyclerView() {
        mAdapter = new UsersListAdapter(vmList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RxRecyclerView.scrollStateChanges(recyclerView)
                .filter(integer -> mAdapter.getItemCount() != 0 && integer == RecyclerView.SCROLL_STATE_IDLE)
                .map(integer -> (mAdapter.getItemCount() - 1 == linearLayoutManager.findLastVisibleItemPosition()))
                .doOnNext(aBoolean -> {
                    if (aBoolean)
                        vm.loadData();
                }).doOnError(throwable -> Log.d(TAG, throwable.getCause().getMessage())).subscribe();
        recyclerView.setAdapter(mAdapter);
    }

    //endregion

}
