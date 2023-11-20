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
        Galleta galleta=new Galleta();
        galleta.setTieneGluten(false);
        Pedido pedidoEsperado= new Pedido(123,"maria","11/09/2023", 1500.0F,galleta);
        Pedido pedidoEsperado2= new Pedido(1234,"jose","11/09/2023", 1600.0F,galleta);
        Flux<Pedido> pedidosEsperados= Flux.just(pedidoEsperado,pedidoEsperado2);
        when(pedidoService.findAll()).thenReturn(pedidosEsperados);
        Flux<Pedido> resultado=pedidoController.getPedidos();
        resultado.subscribe();
        assertEquals(pedidosEsperados,resultado);

    }
    @Test
    void testGetPedidoByIdOk(){
        Galleta galleta=new Galleta();
        galleta.setTieneGluten(false);
        Pedido pedidoEsperado= new Pedido(123,"maria","11/09/2023", 1500.0F, galleta);
        Mono<Pedido> mono= Mono.just(pedidoEsperado);
        when(pedidoService.findById(any())).thenReturn(mono);
        Mono<Pedido> resultado=pedidoController.getPedidoById(123);
        resultado.subscribe();
        assertEquals(mono,resultado);

    }

}
