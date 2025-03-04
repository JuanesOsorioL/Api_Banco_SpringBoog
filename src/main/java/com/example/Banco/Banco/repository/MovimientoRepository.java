package com.example.Banco.Banco.repository;

import com.example.Banco.Banco.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    Optional<Movimiento> findByMovimientoId(Long numeroCuenta);
}