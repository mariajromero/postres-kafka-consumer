package com.example.demo.controller;

import com.example.demo.models.Dona;
import com.example.demo.models.Pedido;
import com.example.demo.service.DonaService;
import com.example.demo.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/donas")
@AllArgsConstructor
public class DonaController {
    private DonaService donaService;
    @PostMapping("/")
    public Mono<Dona> crearDona(@RequestBody Dona dona) {
        return donaService.save(dona);
    }

    @GetMapping("/")
    public Flux<Dona> getDonas(){
        return donaService.findAll();
    }
    @GetMapping("/dona")
    public Mono<Dona> getDonaById(Integer id){
        return donaService.findById(id);
    }
}
