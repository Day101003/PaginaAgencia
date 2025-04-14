package com.agencia.agencia.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenes")
public class Orden {
    
@Id
    
    @Column(name = "id_orden", nullable = false)
    private int id_orden;
    @Column(name = "fecha_orden", nullable = false)
    private LocalDate fecha_orden;
    @Column(name = "precio", nullable = false)
    private int precio;
    @Column(name = "estado_pago", nullable = false)
    private boolean estado_pago;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarios", nullable = true)
    private Usuario usuario;

    public Orden() {
    }

    public Orden(int id_orden, LocalDate fecha_orden, int precio, boolean estado_pago, Usuario usuario) {
        this.id_orden = id_orden;
        this.fecha_orden = fecha_orden;
        this.precio = precio;
        this.estado_pago = estado_pago;
        this.usuario = usuario;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public LocalDate getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(LocalDate fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(boolean estado_pago) {
        this.estado_pago = estado_pago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
