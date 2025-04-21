package com.agencia.agencia.service.impl;

import com.agencia.agencia.model.Usuario;
import com.agencia.agencia.repository.UsuarioRepository;
import com.agencia.agencia.service.UsuarioService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.*;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario loginOrRegister(Payload payload) {
        try {
            String email = payload.getEmail();
            System.out.println("🔍 Buscando usuario con correo: " + email);

            Optional<Usuario> existente = usuarioRepository.findByCorreo(email);

            if (existente.isPresent()) {
                System.out.println("🟡 Usuario ya existe: " + email);
                return existente.get();
            }

            Usuario nuevo = new Usuario();
            nuevo.setCorreo(email);
            nuevo.setNombre((String) payload.get("name"));
            nuevo.setRuta_imagen_usuario("default.png");
            nuevo.setTipo_usuario(2);
            nuevo.setContrasena(passwordEncoder.encode(UUID.randomUUID().toString()));
            nuevo.setFecha_registro(LocalDate.now());
            nuevo.setTelefono("00000000");

            Usuario guardado = usuarioRepository.save(nuevo);
            System.out.println("✅ Usuario creado en BD: " + guardado.getCorreo());
            return guardado;

        } catch (Exception e) {
            System.out.println("🔥 [Error en UsuarioServiceImpl.loginOrRegister] " + e.getMessage());
            e.printStackTrace(); 
            throw e;
        }
    }

    @Override
    public Usuario loginOrRegisterOAuth(OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        Optional<Usuario> existente = usuarioRepository.findByCorreo(email);

        if (existente.isPresent()) {
            return existente.get();
        }

        Usuario nuevo = new Usuario();
        nuevo.setCorreo(email);
        nuevo.setNombre(oauth2User.getAttribute("name"));
        nuevo.setRuta_imagen_usuario("default.png");
        nuevo.setTipo_usuario(2);
        nuevo.setContrasena(passwordEncoder.encode(UUID.randomUUID().toString()));
        nuevo.setFecha_registro(LocalDate.now());
        nuevo.setTelefono("00000000");

        return usuarioRepository.save(nuevo);
    }

    @Override
    public Usuario registrarUsuario(Usuario u) {
        u.setFecha_registro(LocalDate.now());
        u.setContrasena(passwordEncoder.encode(u.getContrasena()));
        return usuarioRepository.save(u);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public Usuario consultar(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) {
        return usuarioRepository.save(u);
    }

    @Override
    public void eliminar(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return User.withUsername(usuario.getCorreo())
                .password(usuario.getContrasena())
                .authorities("ROLE_USER")
                .build();
    }
}
