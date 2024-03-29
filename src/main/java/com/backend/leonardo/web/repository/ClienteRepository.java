package com.backend.leonardo.web.repository;

import com.backend.leonardo.model.Cliente;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends ReactiveCrudRepository<Cliente, Integer> {

}
