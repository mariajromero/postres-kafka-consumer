package com.example.demo.repositories;

import com.example.demo.models.Galleta;
import com.example.demo.models.Pedido;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalletaRepository extends R2dbcRepository<Galleta, Integer> {
}
