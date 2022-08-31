package com.autumn.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "entidadeslegales")
public class EntidadLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entidadlegal_id")
    private Long id;
    @Column(name = "entidadlegal_nombre")
    @NotEmpty
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
