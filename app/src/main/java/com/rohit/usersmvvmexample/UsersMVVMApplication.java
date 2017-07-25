package com.rohit.usersmvvmexample;

import android.app.Application;

import com.rohit.usersmvvmexample.dagger.component.DaggerObjectsComponent;
import com.rohit.usersmvvmexample.dagger.component.ObjectsComponent;
import com.rohit.usersmvvmexample.dagger.module.ObjectsModule;

public class UsersMVVMApplication extends Application {

    //region Variables

    private ObjectsComponent objectsComponent;

    //endregion

    //region Override Methods

    @Override
    public void onCreate() {
        super.onCreate();
        loadObjectsComponent();
    }

    //endregion

    //region Dagger Helper Methods

    public void loadObjectsComponent() {
        //Initialize dagger 2 components
        objectsComponent = DaggerObjectsComponent.builder()
                .objectsModule(new ObjectsModule(this)).build();
    }

    public ObjectsComponent getObjectsComponent() {
        return objectsComponent;
    }

    //endregion

}
