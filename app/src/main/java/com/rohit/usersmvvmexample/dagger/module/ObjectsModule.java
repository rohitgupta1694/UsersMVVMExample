package com.rohit.usersmvvmexample.dagger.module;

import android.app.Application;

import com.rohit.usersmvvmexample.models.UsersList;

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
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .initialData(realm1 -> realm1.createObject(UsersList.class))
                .build();
//        Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getInstance(realmConfig);
    }

    //endregion

    @Provides
    @Singleton
    Realm provideRealm() {
        return realm;
    }

}