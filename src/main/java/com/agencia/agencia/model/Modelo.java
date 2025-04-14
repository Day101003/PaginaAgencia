package com.agencia.agencia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "modelos")
public class Modelo {
    @Id
    @Column(name= "id_modelo", nullable= false)
    private int id_modelo;
    @Column(name= "nombre_tipo", nullable= false)
    private String nombre_tipo;


    public Modelo() {
    }
    public Modelo(int id_modelo, String nombre_tipo) {
        this.id_modelo = id_modelo;
        this.nombre_tipo = nombre_tipo;
    }
    public int getId_modelo() {
        return id_modelo;
    }
    public void setId_modelo(int id_modelo) {
        this.id_modelo = id_modelo;
    }
    public String getNombre_Tipo() {
        return nombre_tipo;
    }
    public void setNombre_Tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }


    
}
