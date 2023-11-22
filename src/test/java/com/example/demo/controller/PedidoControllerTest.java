package com.example.demo.controller;

import com.example.demo.models.Galleta;
import com.example.demo.models.Pedido;
import com.example.demo.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PedidoControllerTest {
    @Mock
    private PedidoService pedidoService;
    @InjectMocks
    private PedidoController pedidoController;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetPedidosOk(){

        Pedido pedidoEsperado= new Pedido(123,"maria","11/09/2023", 1500.0F);
        Pedido pedidoEsperado2= new Pedido(1234,"jose","11/09/2023", 1600.0F);
        Flux<Pedido> pedidosEsperados= Flux.just(pedidoEsperado,pedidoEsperado2);
        when(pedidoService.findAll()).thenReturn(pedidosEsperados);
        Flux<Pedido> resultado=pedidoController.getPedidos();
        resultado.subscribe();
        assertEquals(pedidosEsperados,resultado);

    }
    @Test
    void testGetPedidoByIdOk(){
        Pedido pedidoEsperado= new Pedido(123,"maria","11/09/2023", 1500.0F);
        Mono<Pedido> mono= Mono.just(pedidoEsperado);
        when(pedidoService.findById(any())).thenReturn(mono);
        Mono<Pedido> resultado=pedidoController.getPedidoById(123);
        resultado.subscribe();
        assertEquals(mono,resultado);

    }
    @Test
    void borrarOk(){
        Mono<Void> mono=Mono.empty();
        when(pedidoService.deleteAll()).thenReturn(mono);
        Mono<Void> resultado=pedidoController.deletePedidos();
        resultado.subscribe();
        assertEquals(mono,resultado);
    }
    @Test
    void borrarByIdOk(){
        Pedido pedidoEsperado= new Pedido(123,"maria","11/09/2023", 1500.0F);
        Mono<Pedido> mono= Mono.just(pedidoEsperado);
        when(pedidoService.deleteById(any())).thenReturn(mono);
        Mono<Pedido> resultado=pedidoController.deletePedidoById(123);
        resultado.subscribe();
        assertEquals(mono,resultado);
    }
    @Test
    void updateOk(){

        Pedido pedido= new Pedido(123,"maria","11/09/2023", 1500.0F);
        Mono<Pedido> mono= Mono.just(pedido);
        when(pedidoService.update(any())).thenReturn(mono);
        Mono<Pedido> resultado=pedidoController.updatePedido(pedido);
        resultado.subscribe();
        Pedido nuevo=resultado.block();
        assertEquals(pedido.getNombre(),nuevo.getNombre());

    }

}
