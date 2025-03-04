package com.example.Banco.Banco.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimientoId;

    private String fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "numero_cuenta", nullable = false)
    private Cuenta cuenta;
}
