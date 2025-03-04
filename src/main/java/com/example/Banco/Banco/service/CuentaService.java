package com.example.Banco.Banco.service;

import com.example.Banco.Banco.Component.ClienteMapper;
import com.example.Banco.Banco.Component.CuentaMapper;
import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.model.Cuenta;
import com.example.Banco.Banco.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final ClienteMapper clienteMapper;
    private final CuentaMapper cuentaMapper;
    private final CuentaRepository cuentaRepository;
    public final ClienteService clienteService;

    public CuentaService(ClienteMapper clienteMapper, CuentaMapper cuentaMapper, CuentaRepository cuentaRepository, ClienteService clienteService) {
        this.clienteMapper = clienteMapper;
        this.cuentaMapper = cuentaMapper;
        this.cuentaRepository = cuentaRepository;
        this.clienteService = clienteService;
    }

    public Optional<CuentaDTO> save(CuentaDTO cuentaDTO) {
        if (cuentaRepository.existsByNumeroCuenta(cuentaDTO.getNumeroCuenta())) {
            return Optional.empty();
        }

        return clienteService.findByIdentificacion(cuentaDTO.getClienteId())
                .map(clienteDTO -> {
                    Cuenta cuenta = cuentaMapper.toEntity(cuentaDTO);
                    cuenta.setCliente(clienteMapper.toEntity(clienteDTO));
                    Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
                    return cuentaMapper.toDTO(cuentaGuardada);
                });
    }

    public Optional<CuentaDTO> findByNumeroCuenta(Long account) {
        return cuentaRepository.findByNumeroCuenta(account)
                .map(cuentaMapper::toDTO);
    }

    public List<CuentaDTO> findAll() {
        return cuentaMapper.toDTOList(cuentaRepository.findAll());
    }

    public Optional<CuentaDTO> delete(Long account) {
        return cuentaRepository.findByNumeroCuenta(account)
                .map(cuenta -> {
                            cuentaRepository.delete(cuenta);
                            return cuentaMapper.toDTO(cuenta);
                        }
                );
    }

    public Optional<CuentaDTO> update(CuentaDTO cuentaDTO) {
        return cuentaRepository.findByNumeroCuenta(cuentaDTO.getNumeroCuenta())
                .flatMap(existingAccount -> clienteService.findByIdentificacion(existingAccount.getCliente().getIdentificacion())
                        .map(clienteDTO -> {
                            existingAccount.setSaldoInicial(cuentaDTO.getSaldoInicial());
                            existingAccount.setEstado(cuentaDTO.getEstado());
                            existingAccount.setTipoCuenta(cuentaDTO.getTipoCuenta());
                            existingAccount.setCliente(clienteMapper.toEntity(clienteDTO));
                            Cuenta cuentaActualizada = cuentaRepository.save(existingAccount);
                            return cuentaMapper.toDTO(cuentaActualizada);
                        })
                );
    }

}