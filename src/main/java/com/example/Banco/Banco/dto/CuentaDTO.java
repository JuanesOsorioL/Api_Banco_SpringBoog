package com.example.Banco.Banco.dto;

import com.example.Banco.Banco.model.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO {

    private Long numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private ClienteDTO clienteDTO;
    private Long clienteId;


    public CuentaDTO(Cuenta cuenta) {
        this.numeroCuenta = cuenta.getNumeroCuenta();
        this.tipoCuenta = cuenta.getTipoCuenta();
        this.saldoInicial = cuenta.getSaldoInicial();
        this.estado = cuenta.getEstado();
        this.clienteId = cuenta.getCliente().getClienteId();
    }

}
