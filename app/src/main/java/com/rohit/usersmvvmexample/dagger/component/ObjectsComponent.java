package com.rohit.usersmvvmexample.dagger.component;

import com.rohit.usersmvvmexample.dagger.module.AppModule;
import com.rohit.usersmvvmexample.dagger.module.ObjectsModule;
import com.rohit.usersmvvmexample.fragments.UserListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ObjectsModule.class})
public interface ObjectsComponent {

    void inject(UserListFragment mainActivity);

}
