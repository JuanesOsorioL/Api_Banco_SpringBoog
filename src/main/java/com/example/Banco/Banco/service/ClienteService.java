package com.example.Banco.Banco.service;

import com.example.Banco.Banco.Component.FabricaClienteComponent;
import com.example.Banco.Banco.Component.FabricaClienteComponentComun;
import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.model.Cliente;
import com.example.Banco.Banco.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final FabricaClienteComponent fabricaClienteComponent;

    private final FabricaClienteComponentComun fabricaClienteComponentComun;

    public ClienteService(ClienteRepository clienteRepository, FabricaClienteComponent fabricaClienteComponent, FabricaClienteComponentComun fabricaClienteComponentComun) {
        this.clienteRepository = clienteRepository;
        this.fabricaClienteComponent = fabricaClienteComponent;
        this.fabricaClienteComponentComun = fabricaClienteComponentComun;
    }

    public Optional<ClienteDTO> save(ClienteDTO clienteDTO) {
        if (findByIdentificacion(clienteDTO.getIdentificacion())
                .isPresent()) {
            return Optional.empty();
        }
        Cliente newClient = fabricaClienteComponentComun.criarCliente(clienteDTO);
        Cliente clientSaved = clienteRepository.save(newClient);
        return Optional.of(fabricaClienteComponent.criarClienteDTO(clientSaved));
    }

    public List<ClienteDTO> findAll() {

        return fabricaClienteComponent.listarClientes(clienteRepository.findAll());
    }

    public Optional<ClienteDTO> findByIdentificacion(Long identificacion) {
        return clienteRepository.findClienteByIdentificacion(identificacion)
                .map(fabricaClienteComponent::criarClienteDTO);
    }

    public Optional<ClienteDTO> delete(Long identificacion) {
        return findByIdentificacion(identificacion).map(clienteDTO -> {
            clienteRepository.deleteById(clienteDTO.getIdentificacion());
            return clienteDTO;
        });
    }

    public Optional<ClienteDTO> update(ClienteDTO clienteDTO) {
        System.out.println("clienteDTO = " + clienteDTO);
        return findByIdentificacion(clienteDTO.getIdentificacion())
                .map((clientExist) -> fabricaClienteComponent.criarClienteDTO(clienteRepository.save(fabricaClienteComponentComun.criarCliente(clienteDTO))));
    }

}
