package com.rohit.usersmvvmexample.viewmodel;

import android.view.View;

import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.baseUiComponents.interfaces.MvvmView;
import com.rohit.usersmvvmexample.models.User;

public class UserItemVM extends BaseUserViewModel<MvvmView> {

    //region Constructor Methods

    public UserItemVM(User user) {
        super(user);
        prepareStuff();
    }

    //endregion

    //region Setter Methods

    private void prepareStuff() {
        setId(user.getId());
        setImageUrl(user.getProfilePicture());
        setGenderDrawable(user.getGender().equals("Male") ? R.drawable.superman_icon : R.drawable.wonder_woman_icon);
        setFullName(user.getFullName());
        setEmail(user.getEmail());
        setLikeDrawable(user.getLiked() ? R.drawable.liked : R.drawable.like);
        setLikesTextVisibility(user.getLikesCount() != 0 ? View.VISIBLE : View.GONE);
        setLikes(user.getLikesCount() > 1 ? user.getLikesCount() + " likes" : user.getLikesCount() + " like");
    }

    public void likeButtonClicked() {
        user.setLiked(!user.getLiked());
        user.setLikesCount(user.getLiked() ? user.getLikesCount() + 1 : user.getLikesCount() - 1);
        setLikeDrawable(user.getLiked() ? R.drawable.liked : R.drawable.like);
        setLikesTextVisibility(user.getLikesCount() != 0 ? View.VISIBLE : View.GONE);
        setLikes(user.getLikesCount() > 1 ? user.getLikesCount() + " likes" :
                user.getLikesCount() + " like");
    }

    //endregion

}