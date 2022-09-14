package com.autumn.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;
    @Column(name = "usuario_nombre")
    private String nombre;
    @Column(name = "usuario_apellido")
    private String apellido;
    @Column(name = "usuario_mail")
    private String mail;
    @Column(name = "usuario_contrasena")
    private String contrasena;
    @Column(name = "usuario_rol")
    private String rol;

    @JoinColumn(name = "entidadlegal_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private EntidadLegal entidadLegal;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST}, mappedBy = "ibps")
    private Set<BUFU> bufus = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public EntidadLegal getEntidadLegal() {
        return entidadLegal;
    }

    public void setEntidadLegal(EntidadLegal entidadLegal) {
        this.entidadLegal = entidadLegal;
    }

    public Set<BUFU> getBufus() {
        return bufus;
    }

    public void setBufus(Set<BUFU> bufus) {
        this.bufus = bufus;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", rol='" + rol + '\'' +
                ", entidadLegal=" + entidadLegal +
                '}';
    }
}
