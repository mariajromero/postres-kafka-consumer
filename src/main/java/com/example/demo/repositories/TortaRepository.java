package com.example.demo.repositories;

import com.example.demo.models.Torta;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TortaRepository extends R2dbcRepository<Torta, Integer> {
}
