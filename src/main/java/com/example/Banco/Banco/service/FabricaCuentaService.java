package com.example.Banco.Banco.service;


import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.model.Cliente;
import com.example.Banco.Banco.model.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FabricaCuentaService {

    public final FabricaClienteService fabricaClienteService;


    public FabricaCuentaService( FabricaClienteService fabricaClienteService) {
        this.fabricaClienteService = fabricaClienteService;
    }

    public Cuenta criarCuenta(CuentaDTO cuentaDTO, ClienteDTO clienteDTO) {
       return new Cuenta(cuentaDTO,fabricaClienteService.criarCliente(clienteDTO));
    }

    public CuentaDTO criarCuentaDTO(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaDTO.setEstado(cuenta.getEstado());
        cuentaDTO.setClienteId(cuenta.getCliente().getClienteId());
        return cuentaDTO;
    }

    public List<CuentaDTO> listaCuentas(List<Cuenta> listaCuentas) {
        List<CuentaDTO> listaCuentasDTO = new ArrayList<>();
        listaCuentas.forEach(cuenta -> listaCuentasDTO.add(criarCuentaDTO(cuenta)));
        return listaCuentasDTO;
    }

}
