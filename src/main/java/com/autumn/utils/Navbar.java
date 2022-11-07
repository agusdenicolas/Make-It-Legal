package com.autumn.utils;

import java.util.ArrayList;

public enum Navbar {
    NAVBARGENERAL("navbar-general"), NAVBARCONTRATOS("navbar-contratos");

    private String navbarFlag;

    Navbar(String navbarFlag) {
        this.navbarFlag = navbarFlag;
    }

    public String getNavbarFlag() {
        return navbarFlag;
    }
}
