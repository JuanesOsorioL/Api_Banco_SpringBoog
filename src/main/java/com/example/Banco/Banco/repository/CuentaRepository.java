package com.example.Banco.Banco.repository;

import com.example.Banco.Banco.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> findByNumeroCuenta(Long numeroCuenta);
    boolean existsByNumeroCuenta(Long numeroCuenta);
}
