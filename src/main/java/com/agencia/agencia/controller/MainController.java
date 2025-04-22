// este es un controlador para la pagina principal
package com.agencia.agencia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.agencia.agencia.service.CarrosService;

@Controller
public class MainController {
    
    private final CarrosService carrosService;

    public MainController(CarrosService carrosService) {
        this.carrosService = carrosService;
    }

    @GetMapping("/")  
    public String home(Model model) {
        model.addAttribute("carros", carrosService.listarCarros());
        return "index"; 
    }
}