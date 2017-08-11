package com.rohit.usersmvvmexample.viewholder;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rohit.usersmvvmexample.databinding.UsersListItemLayoutBinding;
import com.rohit.usersmvvmexample.viewmodel.UserItemVM;

public class UserListItemHolder extends RecyclerView.ViewHolder {

    //region Variables

    private UsersListItemLayoutBinding binding;

    //endregion

    //region Constructor Methods

    public UserListItemHolder(UsersListItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    //endregion

    //region View Handling Methods

    public void bind(UserItemVM vm) {
        binding.setVm(vm);
    }

    //endregion

    //region Binding Adapters

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH).
                into(imageView);
    }

    @BindingAdapter("drawable")
    public static void setDrawable(ImageView imageView, int id) {
        Glide.with(imageView.getContext())
                .load(id)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH).
                into(imageView);
    }

    @BindingAdapter("likeDrawable")
    public static void setLikeDrawable(ImageView imageView, int id) {
        Glide.with(imageView.getContext())
                .load(id)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH).
                into(imageView);
    }

    //endregion

    //region Click Listeners

    @BindingAdapter("clicked")
    private void onLikeButtonClicked(View view, UserItemVM userItemVM) {
        userItemVM.likeButtonClicked();
    }

    //endregion

}