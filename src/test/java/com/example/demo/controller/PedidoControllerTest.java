package com.example.demo.controller;

import com.example.demo.models.Pedido;
import com.example.demo.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Pedido pedidoEsperado= new Pedido(123,1500);
        Pedido pedidoEsperado2= new Pedido(1234,1600);
        Flux<Pedido> pedidosEsperados= Flux.just(pedidoEsperado,pedidoEsperado2);
        when(pedidoService.findAll()).thenReturn(pedidosEsperados);
        Flux<Pedido> resultado=pedidoController.getPedidos();
        resultado.subscribe();
        assertEquals(pedidosEsperados,resultado);

    }
}
