package com.example.Banco.Banco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
    private Date fecha;
    private String numeroCuenta; // Asociado a la cuenta
    private Double valor;
    private Double saldo;
    private String tipoMovimiento; // "Depósito" o "Retiro"
}
