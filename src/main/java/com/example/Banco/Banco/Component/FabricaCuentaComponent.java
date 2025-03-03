package com.example.Banco.Banco.Component;


import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.model.Cuenta;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class FabricaCuentaComponent {

    private final FabricaClienteComponentComun  fabricaClienteComponentComun;

    public FabricaCuentaComponent(FabricaClienteComponentComun fabricaClienteComponentComun) {
        this.fabricaClienteComponentComun = fabricaClienteComponentComun;
    }


    //  public final FabricaClienteComponent fabricaClienteComponent;

/*
    public FabricaCuentaComponent(FabricaClienteComponent fabricaClienteComponent) {
        this.fabricaClienteComponent = fabricaClienteComponent;
    }
*/
    public Cuenta criarCuenta(CuentaDTO cuentaDTO, ClienteDTO clienteDTO) {
       return new Cuenta(cuentaDTO, fabricaClienteComponentComun.criarCliente(clienteDTO));
    }
/*
    public CuentaDTO criarCuentaDTO(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaDTO.setEstado(cuenta.getEstado());
        cuentaDTO.setClienteId(cuenta.getCliente().getClienteId());
        return cuentaDTO;
    }
*/
    public List<CuentaDTO> listaCuentas(List<Cuenta> listaCuentas) {
        List<CuentaDTO> listaCuentasDTO = new ArrayList<>();
        listaCuentas.forEach(cuenta -> listaCuentasDTO.add(fabricaClienteComponentComun.criarCuentaDTO(cuenta)));
        return listaCuentasDTO;
    }

}
