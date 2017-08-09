package com.rohit.usersmvvmexample.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private Long mId;
    @SerializedName("full_name")
    private String mFullName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("gender")
    private String mGender;
    @SerializedName("liked")
    private Boolean mLiked;
    @SerializedName("likes_count")
    private Long mLikesCount;
    @SerializedName("profile_picture")
    private String profilePicture;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Boolean getLiked() {
        return mLiked;
    }

    public void setLiked(Boolean liked) {
        mLiked = liked;
    }

    public Long getLikesCount() {
        return mLikesCount;
    }

    public void setLikesCount(Long likesCount) {
        mLikesCount = likesCount;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

}
