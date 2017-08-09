package com.rohit.usersmvvmexample.usersApi;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rohit.usersmvvmexample.models.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersAPI {

    //region Variables

    private Retrofit retrofit;

    //endregion

    //region Constructor

    private UsersAPI() {
        if (retrofit == null) {
            retrofit = getRetrofit();
        }
    }

    //endregion

    //region Instance Methods

    public static UsersAPI getInstance() {
        return new UsersAPI();
    }

    //endregion

    //region Retrofit Helper Methods

    private static Retrofit getRetrofit() {
        OkHttpClient.Builder oKBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.d("OKHTTP", message));
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        oKBuilder.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(oKBuilder.build())
                .build();
    }

    //endregion

    //region Users API

    public Observable<List<User>> getUsersList() {
        UsersEndPoints api = retrofit.create(UsersEndPoints.class);
        return api.getUsersList("598b05ba1100005404515e6a")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    //endregion

}
