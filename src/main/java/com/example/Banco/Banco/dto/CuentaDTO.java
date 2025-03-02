package com.example.Banco.Banco.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
}
