package com.example.demo.controller;

import com.example.demo.models.Pedido;
import com.example.demo.service.PedidoService;
import com.example.demo.service.PostresServiceKafkaConsumer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {
    private PedidoService pedidoService;

    private PostresServiceKafkaConsumer postresServiceKafkaConsumer;
    @GetMapping("v2/topico-kafka/{topico}")
    public Mono<String> obtenerPedidoKafka(@PathVariable String topico) {
        return Mono.just(postresServiceKafkaConsumer.obtenerPedidoKafka(topico));
    }
    @GetMapping("v3/topico-kafka/{topico}")
    public Mono<String> obtenerPedidosKafka(@PathVariable String topico) {
        Flux<Pedido> pedidos=postresServiceKafkaConsumer.obtenerPedidosDesdeKafka(topico);
        return pedidoService.saveAll(pedidos);
    }

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
