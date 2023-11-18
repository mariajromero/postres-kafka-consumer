package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Getter
@Setter
public class Pedido {
    @Id
    private Integer serial;
    private String nombre;
    private String fecha;
    //private Flux<Postre> postres;
    private float precio;

}
