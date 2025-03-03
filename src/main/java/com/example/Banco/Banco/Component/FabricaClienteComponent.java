package com.example.Banco.Banco.Component;

import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.model.Cliente;
import com.example.Banco.Banco.model.Cuenta;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FabricaClienteComponent {

  //  private final FabricaCuentaComponent fabricaCuentaComponent;
private final FabricaClienteComponentComun fabricaClienteComponentComun;

    public FabricaClienteComponent(FabricaClienteComponentComun fabricaClienteComponentComun) {
        this.fabricaClienteComponentComun = fabricaClienteComponentComun;
    }

  /*  public FabricaClienteComponent(FabricaCuentaComponent fabricaCuentaComponent) {
        this.fabricaCuentaComponent = fabricaCuentaComponent;
    }*/
/*
    public Cliente criarCliente(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO);
    }*/

    public ClienteDTO criarClienteDTO(Cliente cliente) {
      //  return new ClienteDTO(cliente);

        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        List<CuentaDTO> cuentasDTO = listarCuentasDTO(cliente.getCuentas());
        clienteDTO.setCuentas(cuentasDTO);
        return clienteDTO;
    }

    public List<CuentaDTO> listarCuentasDTO(List<Cuenta> cuentas) {
        return cuentas.stream()
                .map(fabricaClienteComponentComun::criarCuentaDTO)
                .collect(Collectors.toList());
    }


    public List<ClienteDTO> listarClientes(List<Cliente> ListaClientes) {
        List<ClienteDTO> ListaClienteDTO = new ArrayList<>();
        ListaClientes.forEach(cliente -> ListaClienteDTO.add(criarClienteDTO(cliente)));
        return ListaClienteDTO;
    }
}
