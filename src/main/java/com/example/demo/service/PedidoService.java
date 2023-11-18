package com.example.demo.service;

import com.example.demo.models.Pedido;
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

@Service
@AllArgsConstructor
public class PedidoService {
    private final Logger logger= LoggerFactory.getLogger(PedidoService.class);
    private final PedidoRepository pedidoRepository;
    public Mono<Pedido> save(Pedido pedido){
        return pedidoRepository.save(pedido);

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
    public String deleteById(Integer id){
         pedidoRepository.deleteById(id).onErrorResume(throwable -> {logger.error("error al consultar un pedido con id "+id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido con id "+id+"no encontrado").getMostSpecificCause()));
        String respuesta="se borro el pedido con id"+id;
        return respuesta;
    }
    public String deleteAll(){
        pedidoRepository.deleteAll().onErrorResume(throwable -> {logger.error("error borrando pedidos " ,throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "pedidos no encontrados").getMostSpecificCause()));
        String respuesta="se borroaron los pedidos";
        return respuesta;
    }

}
