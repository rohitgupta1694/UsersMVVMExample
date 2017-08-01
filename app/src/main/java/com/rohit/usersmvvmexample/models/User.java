package com.rohit.usersmvvmexample.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("email")
    private String mEmail;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("gender")
    private String mGender;
    @SerializedName("id")
    private Long mId;
    @SerializedName("likes")
    private Long mLikes;
    @SerializedName("profile_picture")
    private String mProfilePicture;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
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

    public Long getLikes() {
        return mLikes;
    }

    public void setLikes(Long likes) {
        mLikes = likes;
    }

    public String getProfilePicture() {
        return mProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        mProfilePicture = profilePicture;
    }

}
