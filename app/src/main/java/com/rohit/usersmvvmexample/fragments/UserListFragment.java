package com.rohit.usersmvvmexample.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    //region Variables

    private RecyclerView recyclerView;
    private List<UserItemVM> vmList = new ArrayList<>();
    private UsersListAdapter mAdapter;
    private UserListVM vm;

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
        fetchUsers();
        return binding.getRoot();
    }

    private void setupRecyclerView() {
        mAdapter = new UsersListAdapter(vmList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RxRecyclerView.scrollStateChanges(recyclerView)
                .filter(integer -> integer == RecyclerView.SCROLL_STATE_IDLE)
                .map(integer -> (mAdapter.getItemCount() - 1 == linearLayoutManager.findLastVisibleItemPosition()))
                .doOnNext(aBoolean -> {
                    if (aBoolean)
                        vm.loadData();
                }).doOnError(Throwable::printStackTrace);
        recyclerView.setAdapter(mAdapter);
    }

    private void fetchUsers() {
        vm.itemVMList.doOnNext(userItemVMs -> mAdapter.setData(userItemVMs)).subscribe();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((UsersMVVMApplication) getActivity().getApplication()).getObjectsComponent().inject(this);
        setupRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //endregion

}
