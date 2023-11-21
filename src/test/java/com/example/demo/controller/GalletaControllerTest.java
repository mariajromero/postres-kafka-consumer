package com.example.demo.controller;

import com.example.demo.models.Dona;
import com.example.demo.models.Galleta;
import com.example.demo.models.Pedido;
import com.example.demo.service.GalletaService;
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

public class GalletaControllerTest {
    @Mock
    GalletaService galletaService;
    @InjectMocks
    Galletacontroller galletacontroller;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getGalletasOk()
    {
        Galleta g1= new Galleta(123,"vainilla","grande",true,1500.0F);
        Galleta g2= new Galleta(123,"vainilla","grande",false,1500.0F);
        Flux<Galleta> galletas=Flux.just(g1,g2);
        when(galletaService.findAll()).thenReturn(galletas);
        Flux<Galleta> resultado=galletacontroller.getGalletas();
        resultado.subscribe();
        assertEquals(galletas,resultado);
    }
    @Test
    void getGalletaByIdOk(){
        Galleta g1= new Galleta(123,"vainilla","grande",true,1500.0F);
        Mono<Galleta> galletas=Mono.just(g1);
        when(galletaService.findById(any())).thenReturn(galletas);
        Mono<Galleta> resultado=galletacontroller.getGalletaById(123);
        resultado.subscribe();
        assertEquals(galletas,resultado);
    }
}
