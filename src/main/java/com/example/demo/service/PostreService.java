package com.example.demo.service;

import com.example.demo.models.Pedido;
import com.example.demo.models.Postre;
import com.example.demo.repositories.PedidoRepository;
import com.example.demo.repositories.PostreRespository;
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
public class PostreService {
    private final Logger logger= LoggerFactory.getLogger(PedidoService.class);
    private final PostreRespository postreRepository;
    public Mono<Postre> save(Postre pedido){
        return postreRepository.save(pedido);

    }
    public Mono<Postre> findById(Integer id){
        return postreRepository.findById(id).onErrorResume(throwable -> {logger.error("error al consultar un postre con id "+id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "postre con id "+id+"no encontrado").getMostSpecificCause()));

    }
    public Flux<Postre> findAll(){
        return postreRepository.findAll().onErrorResume(throwable -> {logger.error("error al consultar postres ", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error( new ResponseStatusException(HttpStatus.NOT_FOUND, "postres no encontrados").getMostSpecificCause()));

    }
}
