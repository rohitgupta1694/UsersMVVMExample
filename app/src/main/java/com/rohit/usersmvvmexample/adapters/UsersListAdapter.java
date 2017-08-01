package com.rohit.usersmvvmexample.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.viewholder.UserListItemHolder;
import com.rohit.usersmvvmexample.viewmodel.UserItemVM;

import java.util.List;

/**
 * Created by ashraf on 31/7/17.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UserListItemHolder> {
    private List<UserItemVM> itemVMList;

    public UsersListAdapter(List<UserItemVM> itemVMList) {
        this.itemVMList = itemVMList;
    }

    @Override
    public UserListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserListItemHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.users_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(UserListItemHolder holder, int position) {
        holder.bind(itemVMList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemVMList == null ? 0 : itemVMList.size();
    }

    public void setData(List<UserItemVM> userItemVMs) {
        this.itemVMList = userItemVMs;
        notifyDataSetChanged();
    }
}
