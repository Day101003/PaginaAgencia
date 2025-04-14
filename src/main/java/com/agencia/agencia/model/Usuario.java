package com.agencia.agencia.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @Column(name= "id_usuario", nullable= false)
    private int id_usuario ;

    @Column(name= "nombre", nullable= false)
    private String nombre;
    
    @Column(name= "contrasena", nullable= false)
    private String contrasena;
    
    @Column(name= "telefono", nullable= false)
    private String telefono;
    
    @Column(name= "fecha_registro", nullable= false)
    private LocalDate fecha_registro;
    
    @Column(name= "correo", nullable= false)
    private String correo;
    
    @Column(name= "tipo_usuario", nullable= false)
    private int tipo_usuario;
    
    @Column(name= "ruta_imagen_usuario", nullable= false)
    private String ruta_imagen_usuario;
    
    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre, String contrasena, String telefono, LocalDate fecha_registro,
            String correo, int tipo_usuario, String ruta_imagen_usuario) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.fecha_registro = fecha_registro;
        this.correo = correo;
        this.tipo_usuario = tipo_usuario;
        this.ruta_imagen_usuario = ruta_imagen_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getRuta_imagen_usuario() {
        return ruta_imagen_usuario;
    }

    public void setRuta_imagen_usuario(String ruta_imagen_usuario) {
        this.ruta_imagen_usuario = ruta_imagen_usuario;
    }


    
}
