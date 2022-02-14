package com.example.demo.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepository
        extends JpaRepository<Cliente, Integer> {

    //@Query("SELECT s FROM Cliente WHERE s.num_conta = 1?")
    Optional<Cliente> findClienteByNumConta(String nome);

}
