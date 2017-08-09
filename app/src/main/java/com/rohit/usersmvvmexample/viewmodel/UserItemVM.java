package com.rohit.usersmvvmexample.viewmodel;

import android.databinding.ObservableField;

import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.models.User;

public class UserItemVM {

    public long id;
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<Integer> genderDrawable = new ObservableField<>();
    public ObservableField<String> fullName = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> likes = new ObservableField<>();
    public ObservableField<Boolean> liked = new ObservableField<>();

    User user;

    public UserItemVM(User user) {
        this.user = user;
        prepareStuff();
    }

    private void prepareStuff() {
        id = user.getId();
        imageUrl.set(user.getProfilePicture());
        genderDrawable.set(user.getGender().equals("Male") ? R.drawable.superman_icon : R.drawable.wonder_woman_icon);
        fullName.set(user.getFullName());
        email.set(user.getEmail());
        likes.set(user.getLikesCount() > 1 ? user.getLikesCount() + " likes" : user.getLikesCount() + " like");
        liked.set(user.getLiked());
    }

}