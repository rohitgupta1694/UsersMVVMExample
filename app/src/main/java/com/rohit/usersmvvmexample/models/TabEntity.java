package com.rohit.usersmvvmexample.models;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabEntity implements CustomTabEntity {

    //region Variables

    private String tabTitle;
    private int selectedIcon, unselectedIcon;

    //endregion

    //region Constructor

    public TabEntity(int selectedIcon, int unselectedIcon) {
        this.selectedIcon = selectedIcon;
        this.unselectedIcon = unselectedIcon;
    }

    public TabEntity(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    //endregion

    //region Override Methods

    @Override
    public String getTabTitle() {
        return tabTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselectedIcon;
    }

    //endregion

}
