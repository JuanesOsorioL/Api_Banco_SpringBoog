package com.example.Banco.Banco.Component;

import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.model.Cliente;
import com.example.Banco.Banco.model.Cuenta;
import org.springframework.stereotype.Component;

@Component
public class FabricaClienteComponentComun {

    public CuentaDTO criarCuentaDTO(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaDTO.setEstado(cuenta.getEstado());
        cuentaDTO.setClienteId(cuenta.getCliente().getClienteId());
        return cuentaDTO;
    }

    public Cliente criarCliente(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO);
    }
}
