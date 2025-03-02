package com.example.Banco.Banco.repository;

import com.example.Banco.Banco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findClienteByIdentificacion(Long identificacion);
}
