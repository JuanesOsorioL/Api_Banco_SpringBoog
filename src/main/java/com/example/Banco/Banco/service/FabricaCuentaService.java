package com.example.Banco.Banco.service;


import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.model.Cuenta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FabricaCuentaService {

    public Cuenta criarCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.getEstado());
        return cuenta;
    }

    public CuentaDTO criarCuentaDTO(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaDTO.setEstado(cuenta.getEstado());
        return cuentaDTO;
    }

    public List<CuentaDTO> listaCuentas(List<Cuenta> listaCuentas) {
        List<CuentaDTO> listaCuentasDTO = new ArrayList<>();
        listaCuentas.forEach(cuenta -> listaCuentasDTO.add(criarCuentaDTO(cuenta)));
        return listaCuentasDTO;
    }
}
