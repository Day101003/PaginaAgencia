package com.agencia.agencia.controller;

import com.agencia.agencia.model.Usuario;
import com.agencia.agencia.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")    
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 1) Página principal del admin (listado de usuarios)
    @GetMapping
    public String lista(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuario";  
    }

    // 2) Mostrar formulario para crear
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "crearUsuario"; // crearUsuario.html
    }

    // 3) Procesar creación
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        return "redirect:/admin";
    }

    // 4) Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable int id, Model model) {
        Usuario usuario = usuarioService.consultar(id);
        model.addAttribute("usuario", usuario);
        return "editarUsuario"; // editarUsuario.html
    }

    // 5) Procesar actualización
    @PostMapping("/actualizar/{id}")
    public String actualizarUsuario(
            @PathVariable int id,
            @ModelAttribute Usuario usuario) {
        usuario.setId_usuario(id);
        usuarioService.actualizarUsuario(usuario);
        return "redirect:/admin";
    }

    // 6) Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminar(id);
        return "redirect:/admin";
    }
}