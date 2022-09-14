package com.autumn.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "entidadeslegales")
public class EntidadLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entidadlegal_id")
    private Long id;
    @NotEmpty
    @Column(name = "entidadlegal_nombre")
    private String nombre;
    @Column(name = "entidadlegal_activo")
    private boolean isActivo;

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

    public boolean getIsActivo() {
        return isActivo;
    }

    public void setIsActivo(boolean isActivo) {
        this.isActivo = isActivo;
    }

    @Override
    public String toString() {
        return "EntidadLegal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", isActivo=" + isActivo +
                '}';
    }
}
