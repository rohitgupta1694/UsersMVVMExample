package com.rohit.usersmvvmexample.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class UsersList extends RealmObject {

    @SerializedName("response")
    private List<User> mResponse;

    private RealmList<User> realmUsersList;

    public List<User> getResponse() {
        return mResponse;
    }

    public void setResponse(List<User> response) {
        mResponse = response;
    }

    public RealmList<User> getmUsersList() {
        return realmUsersList;
    }

}
