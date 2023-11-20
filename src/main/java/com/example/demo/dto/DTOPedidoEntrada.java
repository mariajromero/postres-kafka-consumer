package com.example.demo.dto;

public record DTOPedidoEntrada(
        Integer serial,
        String nombre,
        String fecha,
        Float precioConDescuento,
        String sabor,
        String tamaño,
        boolean tieneGluten,
        Float precio) {

}
