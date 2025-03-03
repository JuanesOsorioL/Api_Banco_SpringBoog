package com.example.Banco.Banco.service;

import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.model.Cuenta;
import com.example.Banco.Banco.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    public final FabricaCuentaService fabricaCuentaService;
    public final ClienteService clienteService;
    public final FabricaClienteService fabricaClienteService;

    public CuentaService(CuentaRepository cuentaRepository, FabricaCuentaService fabricaCuentaService, ClienteService clienteService, FabricaClienteService fabricaClienteService) {
        this.cuentaRepository = cuentaRepository;
        this.fabricaCuentaService = fabricaCuentaService;
        this.clienteService = clienteService;
        this.fabricaClienteService = fabricaClienteService;
    }

    public Optional<CuentaDTO> save(CuentaDTO cuentaDTO) {
        if (findByNumeroCuenta(cuentaDTO.getNumeroCuenta())
                .isPresent()) {
            return Optional.empty();
        }

        return clienteService.findByIdentificacion(cuentaDTO.getClienteId())
                .map(clienteDTO -> fabricaCuentaService.criarCuentaDTO(
                        cuentaRepository.save(fabricaCuentaService.criarCuenta(
                                cuentaDTO, clienteDTO))));
    }

    public Optional<CuentaDTO> findByNumeroCuenta(Long account) {
        return cuentaRepository.findByNumeroCuenta(account)
                .map(fabricaCuentaService::criarCuentaDTO);
    }

    public List<CuentaDTO> findAll() {
        return fabricaCuentaService.listaCuentas(cuentaRepository.findAll());
    }

    public Optional<CuentaDTO> delete(Long account) {
        return findByNumeroCuenta(account).map(cuentaDTO -> {
            cuentaRepository.deleteById(cuentaDTO.getNumeroCuenta());
            return cuentaDTO;
        });
    }

    public Optional<CuentaDTO> update(CuentaDTO cuentaDTO) {
        Optional<CuentaDTO> newCuentaDTO = findByNumeroCuenta(cuentaDTO.getNumeroCuenta())
                .map(existingAccount -> {
                    existingAccount.setSaldoInicial(cuentaDTO.getSaldoInicial());
                    existingAccount.setEstado(cuentaDTO.getEstado());
                    existingAccount.setTipoCuenta(cuentaDTO.getTipoCuenta());
                    return existingAccount;
                });

        return clienteService.findByIdentificacion(cuentaDTO.getClienteId())
                .map(clienteDTO -> {
                    Cuenta cuentaActualizada = cuentaRepository.save(fabricaCuentaService.criarCuenta(newCuentaDTO.get(), clienteDTO));
                    return fabricaCuentaService.criarCuentaDTO(cuentaActualizada);
                });
    }
}
