package com.example.Banco.Banco.model;

import com.example.Banco.Banco.dto.MovimientoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Date fecha;

    public Movimiento(MovimientoDTO movimientoDTO) {
        this.tipoMovimiento = movimientoDTO.getTipoMovimiento();
        this.valor = movimientoDTO.getValor();
        this.saldo = movimientoDTO.getSaldo();
        this.fecha = movimientoDTO.getFecha();
    }
}
