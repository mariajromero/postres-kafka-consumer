package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
@Setter
public class Dona {
    @Id
    private Integer serial;
    private String sabor;
    private Float precio;
    private boolean conRelleno;



}
