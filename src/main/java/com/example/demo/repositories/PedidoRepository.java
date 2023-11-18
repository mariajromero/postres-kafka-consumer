package com.example.demo.repositories;

import com.example.demo.models.Pedido;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface PedidoRepository extends R2dbcRepository<Pedido, Integer> {

    @Override
    Flux<Pedido> findAll();





}
