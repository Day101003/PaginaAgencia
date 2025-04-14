package com.agencia.agencia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos")
public class Tipo {
    
     @Id
     @Column(name= "id_tipo", nullable= false)
    private int id_tipo;

    @Column(name= "nombre", nullable= false)
    private String nombre;
    
    
    public Tipo() {
    }

    public Tipo(int id_tipo, String nombre) {
        this.id_tipo = id_tipo;
        this.nombre = nombre;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    
    
}
