package com.rohit.usersmvvmexample.usersApi;


import com.rohit.usersmvvmexample.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface UsersEndPoints {

    @GET
    Observable<List<User>> getUsersList(@Url String url);

}