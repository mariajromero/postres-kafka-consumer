package com.example.demo.controller.model;

import com.example.demo.models.Pedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PedidoTest {
    @Test
    void toStringTest(){
        String serial="123";
        String nombre="maria";
        String fecha="hoy";
        String precioConDescuento="1000";
        String string="PedidoExterno{" +
                "serial=" + serial +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", precioConDescuento=" + precioConDescuento +
                '}';
        Pedido pedido=new Pedido(123,"maria","hoy",1000.0F);
        Pedido resultado=Pedido.StringToPedido(string);
        assertEquals(pedido.getNombre(),resultado.getNombre());
        assertEquals(pedido.getFecha(),resultado.getFecha());
        assertEquals(pedido.getPrecioConDescuento(),resultado.getPrecioConDescuento());

    }
}
