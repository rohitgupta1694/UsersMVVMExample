package com.rohit.usersmvvmexample.fragments;

import android.databinding.Bindable;
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

import javax.inject.Inject;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;

public class UserListFragment extends Fragment {

    //region Variables

    private UserListVM vm;
    private LinearLayoutManager linearLayoutManager;

    private OrderedRealmCollection<UserItemVM> vmList;
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
        UsersListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.users_list_fragment, container, false);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        vm = new UserListVM();
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

    @Bindable("adapter")
    public void setAdapter(RecyclerView recyclerView, UsersListAdapter usersListAdapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setScrollListener(recyclerView);
        recyclerView.setAdapter(usersListAdapter);
    }

    private void setScrollListener(RecyclerView recyclerView) {
        RxRecyclerView.scrollStateChanges(recyclerView)
                .filter(integer -> vm.mAdapter.getItemCount() != 0 &&
                        integer == RecyclerView.SCROLL_STATE_IDLE)
                .map(integer -> (vm.mAdapter.getItemCount() - 1 ==
                        linearLayoutManager.findLastVisibleItemPosition()))
                .doOnNext(aBoolean -> {
                    if (aBoolean)
                        vm.loadData();
                }).doOnError(throwable -> Log.d(TAG, throwable.getCause().getMessage())).subscribe();
    }

    private void fetchUsers() {

        vm.usersVMList.doOnNext(users -> {
            //storing data to Realm
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(users);
            realm.commitTransaction();
        });
    }

    //endregion

}
