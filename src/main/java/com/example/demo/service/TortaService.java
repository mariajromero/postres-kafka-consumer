package com.example.demo.service;

import com.example.demo.models.Pedido;
import com.example.demo.models.Torta;
import com.example.demo.repositories.PedidoRepository;
import com.example.demo.repositories.TortaRepository;
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
public class TortaService {
    private final Logger logger= LoggerFactory.getLogger(PedidoService.class);
    private final TortaRepository tortaRepository;
    public Mono<Torta> save(Torta torta){
        return tortaRepository.save(torta);

    }
    public Mono<Torta> findById(Integer id){
        return tortaRepository.findById(id).onErrorResume(throwable -> {logger.error("error al consultar un torta con id "+id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "torta con id "+id+"no encontrado").getMostSpecificCause()));

    }
    public Flux<Torta> findAll(){
        return tortaRepository.findAll().onErrorResume(throwable -> {logger.error("error al consultar torta ", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "torta no encontrados").getMostSpecificCause()));

    }
}
