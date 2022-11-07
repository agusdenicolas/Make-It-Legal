package com.autumn.model;

import javax.persistence.*;

@Entity(name = "estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estado_id")
    private Long id;
    @Column(name = "estado_nombre")
    private String nombre;
    @Column(name = "estado_proximo_estado")
    private Long proximoEstado;
    @Column(name = "estado_proximo_estado_dos")
    private Long proximoEstadoDos;

    //TODO: Workflow model, repo, service
    @JoinColumn(name = "workflow_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Workflow workflow;


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

    public Long getProximoEstado() {
        return proximoEstado;
    }

    public void setProximoEstado(Long proximoEstado) {
        this.proximoEstado = proximoEstado;
    }

    public Long getProximoEstadoDos() {
        return proximoEstadoDos;
    }

    public void setProximoEstadoDos(Long proximoEstadoDos) {
        this.proximoEstadoDos = proximoEstadoDos;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", proximoEstado=" + proximoEstado +
                ", proximoEstadoDos=" + proximoEstadoDos +
                '}';
    }
}
