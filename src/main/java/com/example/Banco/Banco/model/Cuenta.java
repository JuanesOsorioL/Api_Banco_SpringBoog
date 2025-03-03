package com.example.Banco.Banco.model;

import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.dto.CuentaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @Column(unique = true, nullable = false)
    private Long numeroCuenta;

    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Cuenta(CuentaDTO cuentaDTO, Cliente cliente) {
        this.numeroCuenta = cuentaDTO.getNumeroCuenta();
        this.tipoCuenta = cuentaDTO.getTipoCuenta();
        this.saldoInicial = cuentaDTO.getSaldoInicial();
        this.estado = cuentaDTO.getEstado();
        this.cliente = cliente;
    }



    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta=" + numeroCuenta +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", saldoInicial=" + saldoInicial +
                ", estado=" + estado +
                ", cliente=" + cliente.toString() +
                '}';
    }
}
