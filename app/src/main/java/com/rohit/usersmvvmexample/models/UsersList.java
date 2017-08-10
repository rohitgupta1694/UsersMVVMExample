package com.rohit.usersmvvmexample.models;

import io.realm.RealmList;
import io.realm.RealmObject;

public class UsersList extends RealmObject {

    @SuppressWarnings("unused")
    private RealmList<User> realmUsersList;

    public RealmList<User> getmUsersList() {
        return realmUsersList;
    }

}
