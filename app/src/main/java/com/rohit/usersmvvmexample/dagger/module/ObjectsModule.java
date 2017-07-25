package com.rohit.usersmvvmexample.dagger.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class ObjectsModule {

    //region Variables

    Realm realm;

    //endregion

    //region Constructor Methods

    public ObjectsModule(Application application) {
        Realm.init(application.getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);
    }

    //endregion

    @Provides
    @Singleton
    Realm provideRealm() {
        return realm;
    }

}