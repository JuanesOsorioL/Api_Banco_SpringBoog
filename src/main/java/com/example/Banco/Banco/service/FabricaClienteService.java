package com.example.Banco.Banco.service;

import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FabricaClienteService {

    public Cliente criarCliente(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO);
    }

    public ClienteDTO criarClienteDTO(Cliente cliente) {
        return new ClienteDTO(cliente);
    }

    public List<ClienteDTO> listarClientes(List<Cliente> ListaClientes) {
        List<ClienteDTO> ListaClienteDTO = new ArrayList<>();
        ListaClientes.forEach(cliente -> ListaClienteDTO.add(criarClienteDTO(cliente)));
        return ListaClienteDTO;
    }
}
