package com.rohit.usersmvvmexample.viewmodel;

import android.view.View;

import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.baseUiComponents.interfaces.MvvmView;
import com.rohit.usersmvvmexample.baseUiComponents.viewModels.BaseUserViewModel;
import com.rohit.usersmvvmexample.models.User;

import io.realm.Realm;
import io.realm.RealmObjectChangeListener;

public class UserItemVM extends BaseUserViewModel<MvvmView> {

    //region Constructor Methods

    public UserItemVM(Long userId, Realm realm) {
        super(userId, realm);
        prepareStuff();
        RealmObjectChangeListener<User> realmObjectChangeListener = (user1, changeSet) -> {
            this.user = user1;
            prepareStuff();
        };
        setRealmChangeListener(realmObjectChangeListener);
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
        realm.executeTransaction(realm1 -> {
            user.setLiked(!user.getLiked());
            user.setLikesCount(user.getLiked() ? user.getLikesCount() + 1 : user.getLikesCount() - 1);
            setLikeDrawable(user.getLiked() ? R.drawable.liked : R.drawable.like);
            setLikesTextVisibility(user.getLikesCount() != 0 ? View.VISIBLE : View.GONE);
            setLikes(user.getLikesCount() > 1 ? user.getLikesCount() + " likes" :
                    user.getLikesCount() + " like");
            update(user);
        });
    }

    //endregion

}