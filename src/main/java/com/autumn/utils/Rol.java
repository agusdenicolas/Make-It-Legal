package com.autumn.utils;

import java.io.Serializable;
import java.util.ArrayList;

public enum Rol implements Serializable {
    USUARIO("Usuario"), IBP("IBP"), IMPUESTOS("Impuestos"), APODERADO("Apoderado");

    private String rol;

    Rol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public static ArrayList<Rol> getRolesNotUsuario(){
        ArrayList<Rol> roles = new ArrayList<Rol>();
        roles.add(IBP);
        roles.add(IMPUESTOS);
        roles.add(APODERADO);
        return roles;
    }
}
