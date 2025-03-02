package com.example.Banco.Banco.service;

import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.model.Cliente;
import com.example.Banco.Banco.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final FabricaClienteService fabricaClienteService;

    public ClienteService(ClienteRepository clienteRepository, FabricaClienteService fabricaClienteService) {
        this.clienteRepository = clienteRepository;
        this.fabricaClienteService = fabricaClienteService;
    }

    public Optional<ClienteDTO> save(ClienteDTO clienteDTO) {
        if (findByIdentificacion(clienteDTO.getIdentificacion())
                .isPresent()) {
            return Optional.empty();
        }
        Cliente newClient = fabricaClienteService.criarCliente(clienteDTO);
        Cliente clientSaved = clienteRepository.save(newClient);
        return Optional.of(fabricaClienteService.criarClienteDTO(clientSaved));
    }

    public List<ClienteDTO> findAll() {
        return fabricaClienteService.listarClientes(clienteRepository.findAll());
    }

    public Optional<ClienteDTO> findByIdentificacion(Long identificacion) {
        return clienteRepository.findClienteByIdentificacion(identificacion)
                .map(fabricaClienteService::criarClienteDTO);
    }

    public Optional<ClienteDTO> delete(Long identificacion) {
        return findByIdentificacion(identificacion).map(clienteDTO -> {
            clienteRepository.deleteById(clienteDTO.getIdentificacion());
            return clienteDTO;
        });
    }

    public Optional<ClienteDTO> update(ClienteDTO clienteDTO) {
        return findByIdentificacion(clienteDTO.getIdentificacion())
                .map((clientExist) -> fabricaClienteService.criarClienteDTO(clienteRepository.save(fabricaClienteService.criarCliente(clienteDTO))));
    }
}
