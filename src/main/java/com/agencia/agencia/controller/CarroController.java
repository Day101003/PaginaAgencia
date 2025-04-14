package com.agencia.agencia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agencia.agencia.service.CarrosService;

@Controller
@RequestMapping("/controller_carro")
public class CarroController {
  
    private final CarrosService carrosService;

    public CarroController(CarrosService carrosService) {
        this.carrosService = carrosService;
    }

    @GetMapping("/gestionar")
    public String gestionarCarros(Model model) {
        model.addAttribute("carros", carrosService.listarCarros());
        return "gestionarCarro"; 
    }

    @GetMapping("/opciones")
    public String opcionesCarro(Model model) {
        model.addAttribute("opcionesCarro", carrosService.listarCarros());
        return "opcionesCarro";
    }

    @GetMapping("/marca")
    public String listaMarca(Model model) {
        model.addAttribute("listaMarca", carrosService.listarCarros());
        return "listaMarca";
    }

    
    @GetMapping("/modelo")
    public String listaModelo(Model model) {
        model.addAttribute("listaModelo", carrosService.listarCarros());
        return "listaModelo";
    }

    
    @GetMapping("/tipo")
    public String listaTipo(Model model) {
        model.addAttribute("listaTipo", carrosService.listarCarros());
        return "listaTipo";
    }

    @GetMapping("/listaCar")
    public String listaCarro(Model model) {
        model.addAttribute("listaCarro", carrosService.listarCarros());
        return "listaCarro"; 
    }
}

