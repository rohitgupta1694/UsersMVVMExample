package com.rohit.usersmvvmexample.viewholder;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rohit.usersmvvmexample.databinding.UsersListItemLayoutBinding;
import com.rohit.usersmvvmexample.viewmodel.UserItemVM;

/**
 * Created by ashraf on 31/7/17.
 */

public class UserListItemHolder extends RecyclerView.ViewHolder {
    private UsersListItemLayoutBinding binding;

    public UserListItemHolder(UsersListItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;


        LottieAnimationView animationView = binding.usersItemLikeButton;
        animationView.setOnClickListener(view -> {
            animationView.loop(false);
            animationView.playAnimation();
        });
    }

    public void bind(final UserItemVM vm) {
        binding.setVm(vm);
    }


    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).
                into(imageView);
    }

    @BindingAdapter("drawable")
    public static void setDrawable(ImageView imageView, int id) {
        Glide.with(imageView.getContext())
                .load(id)
                .diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).
                into(imageView);
    }

}
