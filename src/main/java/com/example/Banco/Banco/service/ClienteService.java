package com.example.Banco.Banco.service;

import com.example.Banco.Banco.Component.ClienteMapper;
import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.model.Cliente;
import com.example.Banco.Banco.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteMapper clienteMapper, ClienteRepository clienteRepository) {
        this.clienteMapper = clienteMapper;
        this.clienteRepository = clienteRepository;
    }

    public Optional<ClienteDTO> save(ClienteDTO clienteDTO) {
        if (findByIdentificacion(clienteDTO.getIdentificacion())
                .isPresent()) {
            return Optional.empty();
        }
        Cliente newClient = clienteMapper.toEntity(clienteDTO);
        Cliente clientSaved = clienteRepository.save(newClient);
        return Optional.of(clienteMapper.toDTO(clientSaved));
    }

    public List<ClienteDTO> findAll() {
        return clienteMapper.toDTOList(clienteRepository.findAll());
    }

    public Optional<ClienteDTO> findByIdentificacion(Long identificacion) {
        return clienteRepository.findByIdentificacion(identificacion)
                .map(clienteMapper::toDTO);
    }

    public Optional<ClienteDTO> delete(Long identificacion) {
        return findByIdentificacion(identificacion).map(clienteDTO -> {
            clienteRepository.deleteById(clienteDTO.getClienteId());
            return clienteDTO;
        });
    }

    public Optional<ClienteDTO> update(ClienteDTO clienteDTO) {
        return findByIdentificacion(clienteDTO.getIdentificacion())
                .map(clienteExistente -> {
                    Cliente clienteActualizado = clienteMapper.toEntity(clienteDTO);
                    clienteActualizado.setClienteId(clienteExistente.getClienteId());
                    Cliente clienteGuardado = clienteRepository.save(clienteActualizado);
                    return clienteMapper.toDTO(clienteGuardado);
                });
    }

    public Optional<ClienteDTO> findByClienteId(Long clienteId) {
        return clienteRepository.findByClienteId(clienteId)
                .map(clienteMapper::toDTO);
    }

}
