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


    //endregion

    //region Injected Dependencies

    @Inject
    Realm realm;
    private RecyclerView recyclerView;
    private List<UserItemVM> vmList = new ArrayList<>();
    private UsersListAdapter mAdapter;
    private UserListVM vm;

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

    private void setupRecyclerView() {
        mAdapter = new UsersListAdapter(vmList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private void fetchUsers() {
        vm.itemVMList.doOnNext(userItemVMs -> {
            mAdapter.setData(userItemVMs);
        }).subscribe();
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

}
