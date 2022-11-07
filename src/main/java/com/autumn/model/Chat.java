package com.autumn.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;
    @NotEmpty
    @Column(name = "chat_mensaje")
    private String mensaje;
    @Column(name = "chat_fecha")
    private Date fecha;

    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(name = "contrato_id")
    @ManyToOne
    private Contrato contrato;

    public Chat() {
    }

    public Chat(Long id, @NotEmpty String mensaje, Date fecha, Usuario usuario) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", mensaje='" + mensaje + '\'' +
                ", fecha=" + fecha +
                ", usuario=" + usuario +
                ", contrato=" + contrato +
                '}';
    }
}
