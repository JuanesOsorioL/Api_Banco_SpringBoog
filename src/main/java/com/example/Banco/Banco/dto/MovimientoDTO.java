package com.example.Banco.Banco.dto;

import com.example.Banco.Banco.model.Movimiento;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
    private Date fecha;
    private String numeroCuenta; // Asociado a la cuenta
    private Double valor;
    private Double saldo;
    private String tipoMovimiento; // "Depósito" o "Retiro"

    public MovimientoDTO(Movimiento movimiento) {
        this.fecha = movimiento.getFecha();
        this.valor = movimiento.getValor();
        this.saldo = movimiento.getSaldo();
        this.tipoMovimiento = movimiento.getTipoMovimiento();
    }
}
