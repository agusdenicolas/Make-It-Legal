package com.autumn.model;

import javax.persistence.*;

@Entity(name = "entidadeslegales")
public class EntidadLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entidadlegal_id")
    private Long id;
    @Column(name = "entidadlegal_nombre")
    private String nombre;
    @Column(name = "entidadlegal_activo")
    private boolean _activo;

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

    public boolean is_activo() {
        return _activo;
    }

    public void set_activo(boolean _activo) {
        this._activo = _activo;
    }

    @Override
    public String toString() {
        return "EntidadLegal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", _activo=" + _activo +
                '}';
    }
}
