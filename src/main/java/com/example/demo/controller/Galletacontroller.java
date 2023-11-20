package com.example.demo.controller;

import com.example.demo.models.Dona;
import com.example.demo.models.Galleta;
import com.example.demo.service.DonaService;
import com.example.demo.service.GalletaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/galletas")
@AllArgsConstructor
public class Galletacontroller {
    private GalletaService galletaService;
    @PostMapping("/")
    public Mono<Galleta> crearGalleta(@RequestBody Galleta galleta) {
        return galletaService.save(galleta);
    }

    @GetMapping("/")
    public Flux<Galleta> getGalletas(){
        return galletaService.findAll();
    }
    @GetMapping("/galleta")
    public Mono<Galleta> getGalletaById(Integer id){
        return galletaService.findById(id);
    }
}
