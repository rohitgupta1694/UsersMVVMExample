package com.rohit.usersmvvmexample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.interfaces.UsersListView;

public class LikedFragment extends Fragment implements UsersListView {

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
//        ((UsersMVVMApplication) getActivity().getApplication()).getObjectsComponent().inject(this);
        /*UsersListFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.users_list_fragment, container, false);
        vm = new UserListVM(getContext(), realm);
        binding.setVm(vm);
        recyclerView = binding.usersFragmentRecyclerView;*/
        return new View(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*initializeViews();
        vm.attachView(this, savedInstanceState);
        vm.loadData();*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        vm.detachView();
    }

    //endregion

    //region Implemented Methods

    @Override
    public void onRefresh(boolean success) {

    }

    //endregion

}
