package com.agencia.agencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agencia.agencia.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{

    
} 
