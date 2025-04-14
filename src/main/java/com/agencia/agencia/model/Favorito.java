package com.agencia.agencia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "favoritos")
public class Favorito {
    @Id
    @Column(name = "id_favorito", nullable = false)
    private int id_favorito;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "carros", nullable = true)
    private Carro carro;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarios", nullable = true)
    private Usuario usuario;

    public Favorito() {
    }

    public Favorito(int id_favorito, Carro carro, Usuario usuario) {
        this.id_favorito = id_favorito;
        this.carro = carro;
        this.usuario = usuario;
    }

    public int getId_favorito() {
        return id_favorito;
    }

    public void setId_favorito(int id_favorito) {
        this.id_favorito = id_favorito;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    

}
