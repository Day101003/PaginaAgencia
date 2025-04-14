package com.agencia.agencia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "marcas")
public class Marca {
    
 @Id
    @Column(name= "id_marca", nullable= false)
    private int id_marca;
    @Column(name= "nombre_marca", nullable= false)
    private String nombre_marca;
    @Column(name= "origen_marca", nullable= false)
    private String origen_marca;

    
    public Marca() {
    }


    public Marca(int id_marca, String nombre_marca, String origen_marca) {
        this.id_marca = id_marca;
        this.nombre_marca = nombre_marca;
        this.origen_marca = origen_marca;
    }


    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

    public String getOrigen_marca() {
        return origen_marca;
    }

    public void setOrigen_marca(String origen_marca) {
        this.origen_marca = origen_marca;
    }


    
    
  
    
}
