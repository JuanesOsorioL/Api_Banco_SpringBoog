package com.example.Banco.Banco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
    private Long movimientoId;
    private String fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;
    private Long numeroCuenta;
}