package com.example.Banco.Banco.dto;

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

    public CuentaDTO(CuentaDTO cuentaDTO) {
        this.numeroCuenta = cuentaDTO.getNumeroCuenta();
        this.tipoCuenta = cuentaDTO.getTipoCuenta();
        this.saldoInicial = cuentaDTO.getSaldoInicial();
        this.estado = cuentaDTO.getEstado();
        this.clienteDTO = cuentaDTO.getClienteDTO();
        this.clienteId = cuentaDTO.getClienteId();
    }

    @Override
    public String toString() {
        return "CuentaDTO{" +
                "numeroCuenta=" + numeroCuenta +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", saldoInicial=" + saldoInicial +
                ", estado=" + estado +
               ", clienteDTO=" + clienteDTO +
                ", clienteId=" + clienteId +
                '}';
    }
}
