package com.example.demo.controller;

import com.example.demo.models.Dona;
import com.example.demo.service.DonaService;
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

public class DonaControllerTest {
    @Mock
    private DonaService donaService;
    @InjectMocks
    private DonaController donaController;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getDonasOkTest(){
        Dona dona1= new Dona(123,"chocolate", 1500.0F,false);
        Dona dona2= new Dona(123,"chocolate", 1500.0F,true);
        Flux<Dona> donas=Flux.just(dona1,dona2);
        when(donaService.findAll()).thenReturn(donas);
        Flux<Dona> resultado=donaController.getDonas();
        resultado.subscribe();
        assertEquals(donas,resultado);
    }
    @Test
    void getDonaByIdOkTest(){
        Dona dona1= new Dona(123,"chocolate", 1500.0F,false);
        Mono<Dona> donas=Mono.just(dona1);
        when(donaService.findById(any())).thenReturn(donas);
        Mono<Dona> resultado=donaController.getDonaById(123);
        resultado.subscribe();
        assertEquals(donas,resultado);
    }
}
