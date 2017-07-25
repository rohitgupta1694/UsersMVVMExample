package com.rohit.usersmvvmexample.dagger.component;

import com.rohit.usersmvvmexample.activities.MainActivity;
import com.rohit.usersmvvmexample.dagger.module.AppModule;
import com.rohit.usersmvvmexample.dagger.module.ObjectsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ObjectsModule.class})
public interface ObjectsComponent {

    void inject(MainActivity mainActivity);

}
