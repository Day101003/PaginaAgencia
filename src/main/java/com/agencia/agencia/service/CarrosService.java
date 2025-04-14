package com.agencia.agencia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agencia.agencia.model.Carro;
import com.agencia.agencia.model.Usuario;
import com.agencia.agencia.repository.CarroRepository;

@Service
public class CarrosService {
    private final CarroRepository carroRepository;

    public CarrosService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    
    public Carro add(Carro carro) {
        return carroRepository.save(carro);
    }

    public List<Carro> listarCarros(){
        return carroRepository.findAll();
    }

    public Carro consultar(int id_carro){
        return carroRepository.findById(id_carro).orElse(null);
    }

    public void eliminar(int id_carro){
        carroRepository.deleteById(id_carro);
    }

    public  Carro actualizaUsuario(Carro id_carro){
        return carroRepository.save(id_carro);
    }
}
