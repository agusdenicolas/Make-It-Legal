package com.autumn.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "bufu")
public class BUFU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bufu_id")
    private Long id;
    @NotEmpty
    @Column(name = "bufu_nombre")
    private String nombre;
    @Column(name = "bufu_activo")
    private boolean isActivo;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "usuarios_bufu", joinColumns = {@JoinColumn(name = "bufu_id")},
            inverseJoinColumns = {@JoinColumn(name = "usuario_id")})
    private Set<Usuario> ibps = new HashSet<>();

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

    public Set<Usuario> getIbps() {
        return ibps;
    }

    public void setIbps(Set<Usuario> ibps) {
        this.ibps = ibps;
    }

    public void addIbp(Usuario usuario){
        this.ibps.add(usuario);
        usuario.getBufus().add(this);
    }

    public void removeIbp(Usuario usuario){
        this.ibps.remove(usuario);
        usuario.getBufus().remove(this);
    }

    //TODO: Borrar pq no se usa en ningun lado
//    public Usuario findUserById(Long id){
//        Usuario user = this.ibps.stream().filter(ibp -> ibp.getId() == id).findFirst().get();
//        return user;
//    }

    @Override
    public String toString() {
        return "BUFU{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", isActivo=" + isActivo +
                '}';
    }
}
