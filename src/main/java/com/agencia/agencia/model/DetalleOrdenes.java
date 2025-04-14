package com.agencia.agencia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_ordenes")
public class DetalleOrdenes {
    
@Id
  @Column(name = "id_detalle", nullable = false)
    private int id_detalle;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Column(name = "precio_unitario", nullable = false)
    private double precio_unitario;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "carros", nullable = true)
    private Carro carro;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ordenes", nullable = true)
    private Orden orden;

    public DetalleOrdenes() {
    }

    public DetalleOrdenes(int id_detalle, int cantidad, double precio_unitario, Carro carro, Orden orden) {
        this.id_detalle = id_detalle;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.carro = carro;
        this.orden = orden;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    
}
