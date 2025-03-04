package com.example.Banco.Banco.service;

import com.example.Banco.Banco.Component.MovimientoMapper;
import com.example.Banco.Banco.Exception.SaldoInsuficienteException;
import com.example.Banco.Banco.Exception.TipoMovimientoInvalidoException;
import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.dto.MovimientoDTO;
import com.example.Banco.Banco.model.Cliente;
import com.example.Banco.Banco.model.Cuenta;
import com.example.Banco.Banco.model.Movimiento;
import com.example.Banco.Banco.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    private final MovimientoMapper movimientoMapper;
    private final MovimientoRepository movimientoRepository;
    private final ClienteService clienteService;
    private final CuentaService cuentaService;

    public MovimientoService(MovimientoMapper movimientoMapper, MovimientoRepository movimientoRepository, ClienteService clienteService, CuentaService cuentaService) {
        this.movimientoMapper = movimientoMapper;
        this.movimientoRepository = movimientoRepository;
        this.clienteService = clienteService;
        this.cuentaService = cuentaService;
    }

    private MovimientoDTO getMovimientoDTO(CuentaDTO cuentaDTO, ClienteDTO clienteDTO, Movimiento movimiento) {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(cuentaDTO.getId());
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.getEstado());

        Cliente cliente = new Cliente();
        cliente.setClienteId(clienteDTO.getClienteId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setGenero(clienteDTO.getGenero());
        cliente.setEdad(clienteDTO.getEdad());
        cliente.setIdentificacion(clienteDTO.getIdentificacion());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setEstado(clienteDTO.getEstado());

        cuenta.setCliente(cliente);
        movimiento.setCuenta(cuenta);

        Movimiento savedMovimiento = movimientoRepository.save(movimiento);
        return movimientoMapper.toDTO(savedMovimiento);
    }

    public List<MovimientoDTO> findAll() {
        return movimientoMapper.toDTOList(movimientoRepository.findAll());
    }

    public Optional<MovimientoDTO> findByMovimientoId(Long id) {
        return movimientoRepository.findByMovimientoId(id)
                .map(movimientoMapper::toDTO);
    }

    public Optional<MovimientoDTO> save(MovimientoDTO movimientoDTO) {
        return cuentaService.findByNumeroCuenta(movimientoDTO.getNumeroCuenta())
                .flatMap(cuentaDTO -> clienteService.findByClienteId(cuentaDTO.getClienteId())
                        .flatMap(clienteDTO -> {
                            Movimiento movimiento = movimientoMapper.toEntity(movimientoDTO);
                            BigDecimal nuevoSaldo;
                            if (movimientoDTO.getTipoMovimiento().equalsIgnoreCase("Retiro")) {
                                nuevoSaldo = cuentaDTO.getSaldoInicial().subtract(movimientoDTO.getValor());
                                if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
                                    throw new SaldoInsuficienteException("Saldo insuficiente para realizar el retiro");
                                }
                            } else if (movimientoDTO.getTipoMovimiento().equalsIgnoreCase("Deposito")) {
                                nuevoSaldo = cuentaDTO.getSaldoInicial().add(movimientoDTO.getValor());
                            } else {
                                throw new TipoMovimientoInvalidoException("El tipo de movimiento es inválido. Solo se permite 'Retiro' o 'Deposito'");
                            }

                            cuentaDTO.setSaldoInicial(nuevoSaldo);
                            movimiento.setSaldo(nuevoSaldo);
                            cuentaService.update(cuentaDTO);

                            return Optional.of(getMovimientoDTO(cuentaDTO, clienteDTO, movimiento));
                        })
                );
    }

    public Optional<MovimientoDTO> update(MovimientoDTO movimientoDTO) {
        return movimientoRepository.findById(movimientoDTO.getMovimientoId())
                .flatMap(existingMovimiento -> cuentaService.findByNumeroCuenta(movimientoDTO.getNumeroCuenta())
                        .flatMap(cuentaDTO -> clienteService.findByClienteId(cuentaDTO.getClienteId())
                                .map(clienteDTO -> {
                                    existingMovimiento.setFecha(movimientoDTO.getFecha());
                                    return getMovimientoDTO(cuentaDTO, clienteDTO, existingMovimiento);
                                })
                        )
                );
    }

    public Optional<MovimientoDTO> delete(Long id) {
        return movimientoRepository.findByMovimientoId(id)
                .map(cuenta -> {
                            movimientoRepository.delete(cuenta);
                            return movimientoMapper.toDTO(cuenta);
                        }
                );
    }
}