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
    @PostMapping("/update")
    public Mono<Pedido> updatePedido(@RequestBody Pedido pedido) {
        return pedidoService.save(pedido);
    }

    @GetMapping("/")
    public Flux<Pedido> getPedidos(){
        return pedidoService.findAll();
    }
    @GetMapping("/pedido")
    public Mono<Pedido> getPedidoById(Integer id){
        return pedidoService.findById(id);
    }
    @GetMapping("/deleteAll")
    public String deletePedidoById(Integer id){
        return pedidoService.deleteById(id);
    }
    @GetMapping("/delete")
    public String deletePedidos(){
        return pedidoService.deleteAll();
    }


}
