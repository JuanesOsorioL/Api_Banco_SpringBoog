package com.example.Banco.Banco.service;

import com.example.Banco.Banco.Component.FabricaClienteComponent;
import com.example.Banco.Banco.Component.FabricaClienteComponentComun;
import com.example.Banco.Banco.Component.FabricaCuentaComponent;
import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.model.Cuenta;
import com.example.Banco.Banco.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    public final FabricaCuentaComponent fabricaCuentaComponent;
    public final ClienteService clienteService;
    public final FabricaClienteComponent fabricaClienteComponent;

    public final FabricaClienteComponentComun fabricaClienteComponentComun;

    public CuentaService(CuentaRepository cuentaRepository, FabricaCuentaComponent fabricaCuentaComponent, ClienteService clienteService, FabricaClienteComponent fabricaClienteComponent, FabricaClienteComponentComun fabricaClienteComponentComun) {
        this.cuentaRepository = cuentaRepository;
        this.fabricaCuentaComponent = fabricaCuentaComponent;
        this.clienteService = clienteService;
        this.fabricaClienteComponent = fabricaClienteComponent;


        this.fabricaClienteComponentComun = fabricaClienteComponentComun;
    }

    public Optional<CuentaDTO> save(CuentaDTO cuentaDTO) {
        if (findByNumeroCuenta(cuentaDTO.getNumeroCuenta())
                .isPresent()) {
            return Optional.empty();
        }

        return clienteService.findByIdentificacion(cuentaDTO.getClienteId())
                .map(clienteDTO -> fabricaClienteComponentComun.criarCuentaDTO(
                        cuentaRepository.save(fabricaCuentaComponent.criarCuenta(
                                cuentaDTO, clienteDTO))));
    }

    public Optional<CuentaDTO> findByNumeroCuenta(Long account) {
        return cuentaRepository.findByNumeroCuenta(account)
                .map(fabricaClienteComponentComun::criarCuentaDTO);
    }

    public List<CuentaDTO> findAll() {
        return fabricaCuentaComponent.listaCuentas(cuentaRepository.findAll());
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
                    Cuenta cuentaActualizada = cuentaRepository.save(fabricaCuentaComponent.criarCuenta(newCuentaDTO.get(), clienteDTO));
                    return fabricaClienteComponentComun.criarCuentaDTO(cuentaActualizada);
                });
    }
}
