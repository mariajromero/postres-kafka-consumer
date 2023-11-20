package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="pedidos")
public class Pedido {
    @Id
    @jakarta.persistence.Id
    private Integer serial;
    private String nombre;
    private String fecha;
    //private Flux<Postre> postres;
    private Float precioConDescuento;
    @OneToOne
    @MapsId
    @JoinColumn(name="id")
    private Galleta galleta;

}
