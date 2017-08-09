package com.rohit.usersmvvmexample.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.models.User;
import com.rohit.usersmvvmexample.viewholder.UserListItemHolder;
import com.rohit.usersmvvmexample.viewmodel.UserItemVM;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class UsersListAdapter extends RealmRecyclerViewAdapter<User, UserListItemHolder> {

    //region Variables

    private List<UserItemVM> itemVMList;

    //endregion

    //region Constructor Methods

    public UsersListAdapter(OrderedRealmCollection<User> usersList) {
        super(usersList, true, true);
        setHasStableIds(true);
    }

    //endregion

    //region Setter Methods

    public void setData(List<UserItemVM> userItemVMs) {
        this.itemVMList = userItemVMs;
        notifyDataSetChanged();
    }

    public void appendData(List<UserItemVM> userItemVMs) {
        this.itemVMList.addAll(userItemVMs);
        notifyItemRangeInserted(getItemCount(), getItemCount() + userItemVMs.size());
    }

    //endregion

    //region Override Methods

    @Override
    public int getItemCount() {
        return ((itemVMList == null || itemVMList.size() == 0) ? 0 : itemVMList.size());
    }

    @Override
    public UserListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserListItemHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.users_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(UserListItemHolder holder, int position) {
        holder.bind(itemVMList.get(holder.getAdapterPosition()));
    }

    //endregion

}
