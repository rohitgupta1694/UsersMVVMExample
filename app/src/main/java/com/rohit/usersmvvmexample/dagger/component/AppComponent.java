package com.rohit.usersmvvmexample.dagger.component;

import android.app.Application;

import com.rohit.usersmvvmexample.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Application application();
}
