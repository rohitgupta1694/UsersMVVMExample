package com.rohit.usersmvvmexample.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersList {

    @SerializedName("response")
    private List<User> mResponse;

    public List<User> getResponse() {
        return mResponse;
    }

    public void setResponse(List<User> response) {
        mResponse = response;
    }

}
