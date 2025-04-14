package com.agencia.agencia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agencia.agencia.model.Usuario;
import com.agencia.agencia.service.UsuarioService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin")
public class UsuarioController{

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/admin")
public String showAdminPage() {
    return "admin";
}


    @GetMapping
    public String lista(Model model) {
        model.addAttribute("usuario", usuarioService.listarUsuario());
        return "usuario";
    }


    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "crearUsuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.add(usuario);
        return "guardarUsuario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Usuario usuario = usuarioService.consultar(id);
        model.addAttribute("usuario", usuario);
        usuarioService.actualizaUsuario(usuario);
        return "editarUsuario";
    }

}