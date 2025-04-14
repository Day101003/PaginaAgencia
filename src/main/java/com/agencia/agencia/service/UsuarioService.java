package com.agencia.agencia.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.agencia.agencia.model.Usuario;
import com.agencia.agencia.repository.UsuarioRepository;
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario add(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuario(){
        return usuarioRepository.findAll();
    }

    public Usuario consultar(int id_usuario){
        return usuarioRepository.findById(id_usuario).orElse(null);
    }

    public void eliminar(int id_usuario){
        usuarioRepository.deleteById(id_usuario);
    }

    public  Usuario actualizaUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
