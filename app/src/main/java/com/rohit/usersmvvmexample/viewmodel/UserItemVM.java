package com.rohit.usersmvvmexample.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.rohit.usersmvvmexample.BR;
import com.rohit.usersmvvmexample.R;
import com.rohit.usersmvvmexample.models.User;

public class UserItemVM extends BaseObservable {

    //region Variables

    public long id;
    public String email;
    public String likes;
    public Boolean liked;
    public String imageUrl;
    public String fullName;
    public Integer genderDrawable;

    private User user;

    //endregion

    //region Constructor Methods

    public UserItemVM(User user) {
        this.user = user;
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
        setLikes(user.getLikesCount() > 1 ? user.getLikesCount() + " likes" : user.getLikesCount() + " like");
        setLiked(user.getLiked());
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setGenderDrawable(Integer genderDrawable) {
        this.genderDrawable = genderDrawable;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLikes(String likes) {
        this.likes = likes;
        notifyPropertyChanged(BR.likes);
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
        notifyPropertyChanged(BR.likedButtonStatus);
    }

    //endregion

    //region Getter Methods

    @Bindable
    public Boolean getLikedButtonStatus() {
        return liked;
    }

    @Bindable
    public String getLikes() {
        return likes;
    }

    //endregion

}