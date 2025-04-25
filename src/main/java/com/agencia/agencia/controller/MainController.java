// este es un controlador para la pagina principal
package com.agencia.agencia.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agencia.agencia.model.Carro;
import com.agencia.agencia.model.Marca;
import com.agencia.agencia.service.CarrosService;
import com.agencia.agencia.service.MarcaService;

@Controller
public class MainController {

    private final CarrosService carrosService;
    private final MarcaService marcaService;

    public MainController(CarrosService carrosService, MarcaService marcaService) {
        this.carrosService = carrosService;
        this.marcaService = marcaService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("carros", carrosService.listarCarros());
        model.addAttribute("marcas", marcaService.listarMarca());
        return "index";
    }

    @GetMapping("/marcasTipos")
    public String marcasTipos(@RequestParam("id_marca") long idMarca, Model model) {
        List<Carro> carros = carrosService.listarCarros().stream()
                .filter(carro -> carro.getMarca() != null && carro.getMarca().getId_marca() == idMarca)
                .toList();
        model.addAttribute("carros", carros);
        return "marcasTipos";
    }

}