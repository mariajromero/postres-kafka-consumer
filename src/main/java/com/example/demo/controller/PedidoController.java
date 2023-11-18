package com.example.demo.controller;

import com.example.demo.models.Pedido;
import com.example.demo.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {
    private PedidoService pedidoService;
    @PostMapping("/")
    public Mono<Pedido> crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.save(pedido);
    }
    @GetMapping("/")
    public Flux<Pedido> getPedidos(){
        return pedidoService.findAll();
    }
}
