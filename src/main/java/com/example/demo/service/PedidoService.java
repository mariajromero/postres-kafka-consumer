package com.example.demo.service;

import com.example.demo.dto.DTOPedidoEntrada;
import com.example.demo.models.Galleta;
import com.example.demo.models.Pedido;
import com.example.demo.repositories.GalletaRepository;
import com.example.demo.repositories.PedidoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@AllArgsConstructor
public class PedidoService {
    private final Logger logger= LoggerFactory.getLogger(PedidoService.class);
    private final PedidoRepository pedidoRepository;
    private final GalletaRepository galletaRepository;
    public Mono<Pedido> save(Pedido pedido){
        return pedidoRepository.save(pedido);

    }
    public Mono<Pedido> update(Pedido pedido){
        return pedidoRepository.findById(pedido.getSerial())
                .flatMap(pedido1 -> pedidoRepository.save(pedido).thenReturn(pedido))
                .onErrorResume(throwable -> {logger.error("error al consultar  pedido  ", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado").getMostSpecificCause()));
    }

    public Mono<DTOPedidoEntrada> crearPedido(DTOPedidoEntrada dtoPedidoEntrada){
        Galleta galleta=new Galleta();
        galleta.setPrecio(dtoPedidoEntrada.precio());
        galleta.setSabor(dtoPedidoEntrada.sabor());
        galleta.setTamaño(dtoPedidoEntrada.tamaño());
        galleta.setTieneGluten(dtoPedidoEntrada.tieneGluten());
        Pedido pedido=new Pedido();
        pedido.setFecha(dtoPedidoEntrada.fecha());
        pedido.setNombre(dtoPedidoEntrada.nombre());
        pedido.setPrecioConDescuento(galleta.aplicarDescuento(galleta.getPrecio()));
        pedido.setGalleta(galleta);
        return Mono.zip(galletaRepository.save(galleta),
                pedidoRepository.save(pedido)).map(tupla->{
                    Pedido pedidoCreado=tupla.getT2();
                    Galleta galletaCreada=tupla.getT1();
                    return  new DTOPedidoEntrada(pedidoCreado.getSerial(),
                            pedidoCreado.getNombre(),
                            pedidoCreado.getFecha(),
                            pedidoCreado.getPrecioConDescuento(),
                            galletaCreada.getSabor(),
                            galletaCreada.getTamaño(),
                            galletaCreada.isTieneGluten(),
                            galletaCreada.getPrecio()
                    );
        });



    }
    public Mono<Pedido> findById(Integer id){
        return pedidoRepository.findById(id).onErrorResume(throwable -> {logger.error("error al consultar un pedido con id "+id, throwable);
        return Mono.empty();
        })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido con id "+id+"no encontrado").getMostSpecificCause()));

    }
    public Flux<Pedido> findAll(){
        return pedidoRepository.findAll().onErrorResume(throwable -> {logger.error("error al consultar pedidos ", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedidos no encontrados").getMostSpecificCause()));

    }
    public Mono<Pedido> deleteById(Integer id){
        return pedidoRepository.findById(id)
                        .flatMap(pedido -> pedidoRepository.deleteById(pedido.getSerial()).thenReturn(pedido))
                .onErrorResume(throwable -> {logger.error("error al consultar un pedido con id "+id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido con id "+id+"no encontrado").getMostSpecificCause()));

    }
    public Mono<Void> deleteAll(){
        return pedidoRepository.deleteAll().onErrorResume(throwable -> {logger.error("error borrando pedidos " ,throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "pedidos no encontrados").getMostSpecificCause()));


    }

}
