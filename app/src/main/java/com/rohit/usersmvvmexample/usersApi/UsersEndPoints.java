package com.rohit.usersmvvmexample.usersApi;


import com.rohit.usersmvvmexample.models.UsersList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface UsersEndPoints {

    @GET
    Observable<UsersList> getUsersList(@Url String url);

}