package com.example.Banco.Banco.service;

import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FabricaClienteService {

  //  private final FabricaCuentaService fabricaCuentaService;

/*
    public FabricaClienteService( FabricaCuentaService fabricaCuentaService) {
        this.fabricaCuentaService = fabricaCuentaService;
    }
*/
    public Cliente criarCliente(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO);
    }

    public ClienteDTO criarClienteDTO(Cliente cliente) {
        return new ClienteDTO(cliente);

      //  ClienteDTO clienteDTO = new ClienteDTO();
      //  clienteDTO.setCuentasDTO(listarClientes(cliente));
      //  return new ClienteDTO(cliente);
    }
/*
    public List<CuentaDTO> listarClientes(Cliente cliente) {
        List<CuentaDTO> ListaCuentasDTO = new ArrayList<>();
        cliente.getCuentas().forEach(cuenta -> ListaCuentasDTO.add(fabricaCuentaService.criarCuentaDTO(cuenta)));
        return ListaCuentasDTO;
    }
*/

    public List<ClienteDTO> listarClientes(List<Cliente> ListaClientes) {
        List<ClienteDTO> ListaClienteDTO = new ArrayList<>();
        ListaClientes.forEach(cliente -> ListaClienteDTO.add(criarClienteDTO(cliente)));
        return ListaClienteDTO;
    }
}
