package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
public class Galleta {
    @Id
    private Integer serial;
    private String sabor;
    private String tamaño;
    private boolean tieneGluten;


}
