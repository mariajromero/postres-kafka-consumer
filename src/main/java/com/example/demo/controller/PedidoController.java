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
    @PutMapping("/update")
    public Mono<Pedido> updatePedido(@RequestBody Pedido pedido) {
        return pedidoService.update(pedido);
    }

    @GetMapping("/")
    public Flux<Pedido> getPedidos(){
        return pedidoService.findAll();
    }
    @GetMapping("/pedido/{id}")
    public Mono<Pedido> getPedidoById(@PathVariable("id") Integer id){
        return pedidoService.findById(id);
    }
    @GetMapping("/delete/{id}")
    public Mono<Pedido> deletePedidoById(@PathVariable("id")Integer id){
        return pedidoService.deleteById(id);
    }
    @GetMapping("/deleteAll")
    public Mono<Void> deletePedidos(){
        return pedidoService.deleteAll();
    }


}
