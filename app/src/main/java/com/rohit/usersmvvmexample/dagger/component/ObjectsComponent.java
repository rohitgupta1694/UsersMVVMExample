package com.rohit.usersmvvmexample.dagger.component;

import com.rohit.usersmvvmexample.dagger.module.ObjectsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ObjectsModule.class)
public interface ObjectsComponent {
}
