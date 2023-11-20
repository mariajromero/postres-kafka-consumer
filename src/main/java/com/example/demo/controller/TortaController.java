package com.example.demo.controller;

import com.example.demo.models.Galleta;
import com.example.demo.models.Torta;
import com.example.demo.service.GalletaService;
import com.example.demo.service.TortaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tortas")
@AllArgsConstructor
public class TortaController {
    private TortaService tortaService;
    @PostMapping("/")
    public Mono<Torta> crearTorta(@RequestBody Torta torta) {
        return tortaService.save(torta);
    }

    @GetMapping("/")
    public Flux<Torta> getTortas(){
        return tortaService.findAll();
    }
    @GetMapping("/torta")
    public Mono<Torta> getTortaById(Integer id){
        return tortaService.findById(id);
    }
}
