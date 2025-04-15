package com.agencia.agencia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agencia.agencia.model.Carro;
import com.agencia.agencia.model.Marca;
import com.agencia.agencia.service.CarrosService;
import com.agencia.agencia.service.TipoService; 

@Controller
@RequestMapping("/controller_carro")
public class CarroController {
  
    private final CarrosService carrosService;
    private final TipoService tipoService; 

    public CarroController(CarrosService carrosService, TipoService tipoService) {
        this.carrosService = carrosService;
        this.tipoService = tipoService; 
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

    @GetMapping("/listaCar")
    public String listaCarro(Model model) {
        model.addAttribute("listaCarro", carrosService.listarCarros());
        return "listaCarro"; 
    }


    @PostMapping("/guardar")
    public String guardarMarca(@ModelAttribute Carro carro) {
        carrosService.add(carro);
        return "redirect:/controller_carro/listaCar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Carro carro = carrosService.consultar(id);
        model.addAttribute("carros", carro);
        return "editarMarca";
    }

    @PostMapping("/eliminar/{id}")
public String eliminar(@PathVariable int id) {
    carrosService.eliminar(id);
    return "redirect:/controller_carro/listaCar"; 
}






}
