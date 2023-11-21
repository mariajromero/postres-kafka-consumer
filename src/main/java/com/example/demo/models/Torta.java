package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
@Setter
public class Torta implements ISabores{
    @Id
    private Integer serial;
    private String sabor;
    private String tamaño;
    private boolean conCrema;


    @Override
    public String agregarOtroSabor() {
        return this.sabor+" con chocolate";
    }
}
