package com.autumn.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contrato_id")
    private Long id;
    @NotEmpty
    @Column(name = "contrato_contraparte")
    private String contraparte;
    @NotEmpty
    @Column(name = "contrato_descripcion")
    private String descripcion;
    @Column(name = "contrato_fecha_emision")
    private Date fechaEmision;
    @Column(name = "contrato_fecha_firma_legales")
    private Date fechaFirmaLegales;
    @Column(name = "contrato_fecha_firma_usuario")
    private Date fechaFirmaUsuario;
    @Column(name = "contrato_fecha_firma_impuestos")
    private Date fechaFirmaImpuestos;
    @Column(name = "contrato_fecha_firma_apoderado_uno")
    private Date fechaFirmaApoderadoUno;
    @Column(name = "contrato_fecha_firma_apoderado_dos")
    private Date fechaFirmaApoderadoDos;

    @NotNull
    @JoinColumn(name = "entidadlegal_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private EntidadLegal entidadLegal;

    @NotNull
    @JoinColumn(name = "bufu_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private BUFU bufu;

    @NotNull
    @JoinColumn(name = "usuario_id_ibp")
    @ManyToOne(cascade = CascadeType.ALL)
    //Abogado (Usuario del Área de Legales)
    private Usuario ibp;

    @JoinColumn(name = "usuario_id_usuario")
    @ManyToOne(cascade = CascadeType.ALL)
    //Usuario que crea la solicitud
    private Usuario usuario;

    @JoinColumn(name = "estado_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Estado estadoActual;

    @JoinColumn(name = "usuario_id_firma_legales")
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario firmaLegales;

    @JoinColumn(name = "usuario_id_firma_contraparte")
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario firmaContraparte;

    @JoinColumn(name = "usuario_id_firma_impuestos")
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario firmaImpuestos;

    @JoinColumn(name = "usuario_id_firma_apoderado_uno")
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario firmaApoderadoUno;

    @JoinColumn(name = "usuario_id_firma_apoderado_dos")
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario firmaApoderadoDos;

    @JoinColumn(name = "workflow_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Workflow workflow;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContraparte() {
        return contraparte;
    }

    public void setContraparte(String contraparte) {
        this.contraparte = contraparte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaFirmaLegales() {
        return fechaFirmaLegales;
    }

    public void setFechaFirmaLegales(Date fechaFirmaLegales) {
        this.fechaFirmaLegales = fechaFirmaLegales;
    }

    public Date getFechaFirmaUsuario() {
        return fechaFirmaUsuario;
    }

    public void setFechaFirmaUsuario(Date fechaFirmaUsuario) {
        this.fechaFirmaUsuario = fechaFirmaUsuario;
    }

    public Date getFechaFirmaImpuestos() {
        return fechaFirmaImpuestos;
    }

    public void setFechaFirmaImpuestos(Date fechaFirmaImpuestos) {
        this.fechaFirmaImpuestos = fechaFirmaImpuestos;
    }

    public Date getFechaFirmaApoderadoUno() {
        return fechaFirmaApoderadoUno;
    }

    public void setFechaFirmaApoderadoUno(Date fechaFirmaApoderadoUno) {
        this.fechaFirmaApoderadoUno = fechaFirmaApoderadoUno;
    }

    public Date getFechaFirmaApoderadoDos() {
        return fechaFirmaApoderadoDos;
    }

    public void setFechaFirmaApoderadoDos(Date fechaFirmaApoderadoDos) {
        this.fechaFirmaApoderadoDos = fechaFirmaApoderadoDos;
    }

    public EntidadLegal getEntidadLegal() {
        return entidadLegal;
    }

    public void setEntidadLegal(EntidadLegal entidadLegal) {
        this.entidadLegal = entidadLegal;
    }

    public BUFU getBufu() {
        return bufu;
    }

    public void setBufu(BUFU bufu) {
        this.bufu = bufu;
    }

    public Usuario getIbp() {
        return ibp;
    }

    public void setIbp(Usuario ibp) {
        this.ibp = ibp;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Usuario getFirmaLegales() {
        return firmaLegales;
    }

    public void setFirmaLegales(Usuario firmaLegales) {
        this.firmaLegales = firmaLegales;
    }

    public Usuario getFirmaContraparte() {
        return firmaContraparte;
    }

    public void setFirmaContraparte(Usuario firmaContraparte) {
        this.firmaContraparte = firmaContraparte;
    }

    public Usuario getFirmaImpuestos() {
        return firmaImpuestos;
    }

    public void setFirmaImpuestos(Usuario firmaImpuestos) {
        this.firmaImpuestos = firmaImpuestos;
    }

    public Usuario getFirmaApoderadoUno() {
        return firmaApoderadoUno;
    }

    public void setFirmaApoderadoUno(Usuario firmaApoderadoUno) {
        this.firmaApoderadoUno = firmaApoderadoUno;
    }

    public Usuario getFirmaApoderadoDos() {
        return firmaApoderadoDos;
    }

    public void setFirmaApoderadoDos(Usuario firmaApoderadoDos) {
        this.firmaApoderadoDos = firmaApoderadoDos;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", contraparte='" + contraparte + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", fechaFirmaLegales=" + fechaFirmaLegales +
                ", fechaFirmaUsuario=" + fechaFirmaUsuario +
                ", fechaFirmaImpuestos=" + fechaFirmaImpuestos +
                ", fechaFirmaApoderadoUno=" + fechaFirmaApoderadoUno +
                ", fechaFirmaApoderadoDos=" + fechaFirmaApoderadoDos +
                ", entidadLegal=" + entidadLegal +
                ", bufu=" + bufu +
                ", ibp=" + ibp +
                ", usuario=" + usuario +
                ", estadoActual=" + estadoActual +
                ", firmaLegales=" + firmaLegales +
                ", firmaContraparte=" + firmaContraparte +
                ", firmaImpuestos=" + firmaImpuestos +
                ", firmaApoderadoUno=" + firmaApoderadoUno +
                ", firmaApoderadoDos=" + firmaApoderadoDos +
                ", workflow=" + workflow +
                '}';
    }
}
