package com.example.demo.controller;

import com.example.demo.models.Torta;
import com.example.demo.service.TortaService;
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

public class TortaControllerTest {
    @Mock
    TortaService tortaService;
    @InjectMocks
    TortaController tortaController;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getTortasOk(){
        Torta t1=new Torta(123,"fresa","pequeño",true);
        Torta t2=new Torta(123,"fresa","pequeño",false);
        Flux<Torta> tortas= Flux.just(t1,t2);
        when(tortaService.findAll()).thenReturn(tortas);
        Flux<Torta> resultado=tortaController.getTortas();
        resultado.subscribe();
        assertEquals(tortas,resultado);

    }
    @Test
    void getTortaByIdOk(){
        Torta t2=new Torta(123,"fresa","pequeño",false);
        Mono<Torta> tortas= Mono.just(t2);
        when(tortaService.findById(any())).thenReturn(tortas);
        Mono<Torta> resultado=tortaController.getTortaById(123);
        resultado.subscribe();
        assertEquals(tortas,resultado);
    }

}
