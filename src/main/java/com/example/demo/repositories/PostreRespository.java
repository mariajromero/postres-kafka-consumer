package com.example.demo.repositories;

import com.example.demo.models.Pedido;
import com.example.demo.models.Postre;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostreRespository extends R2dbcRepository<Postre, Integer> {
}
