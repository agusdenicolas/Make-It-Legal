package com.autumn.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "workflows")
public class Workflow {

    public static Workflow CARTA_OFERTA_CONTRAPARTE = new Workflow(1L, "Carta Oferta Contraparte");
    public static Workflow CARTA_OFERTA_BASF = new Workflow(2L, "Carta Oferta BASF");
    public static Workflow ACUERDO_DOBLE_FIRMA_ARG = new Workflow(3L, "Acuerdo Doble Firma ARG");
    public static Workflow ACUERDO_DOBLE_FIRMA_UY_PY_BOL = new Workflow(4L, "Acuerdo Doble Firma UY, PY, BOL");

    @Id
    @Column(name = "workflow_id")
    private Long id;
    @Column(name = "workflow_nombre")
    private String workflow;

    public Workflow() {
    }

    public Workflow(Long id, String workflow) {
        this.id = id;
        this.workflow = workflow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    @Override
    public String toString() {
        return "Workflow{" +
                "id=" + id +
                ", workflow='" + workflow + '\'' +
                '}';
    }
}
