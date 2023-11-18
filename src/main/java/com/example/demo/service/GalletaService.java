package com.example.demo.service;

import com.example.demo.models.Galleta;
import com.example.demo.models.Pedido;
import com.example.demo.repositories.GalletaRepository;
import com.example.demo.repositories.PedidoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class GalletaService {
    private final Logger logger= LoggerFactory.getLogger(PedidoService.class);
    private final GalletaRepository galletaRepository;
    public Mono<Galleta> save(Galleta galleta){
        return galletaRepository.save(galleta);

    }
    public Mono<Galleta> findById(Integer id){
        return galletaRepository.findById(id).onErrorResume(throwable -> {logger.error("error al consultar galleta con id "+id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "galleta con id "+id+"no encontrado").getMostSpecificCause()));

    }
    public Flux<Galleta> findAll(){
        return galletaRepository.findAll().onErrorResume(throwable -> {logger.error("error al consultar galletas ", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "galletas no encontrados").getMostSpecificCause()));

    }

}
