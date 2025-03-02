package com.example.Banco.Banco.service;

import com.example.Banco.Banco.dto.MovimientoDTO;
import com.example.Banco.Banco.model.Movimiento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FabricaMovimientosService {

    public Movimiento criarMovimiento(MovimientoDTO movimientoDTO) {
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(movimientoDTO.getFecha());
        movimiento.setSaldo(movimientoDTO.getSaldo());
        movimiento.setValor(movimientoDTO.getValor());
        movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
        return movimiento;
    }

    public MovimientoDTO criarMovimientoDTO(Movimiento movimiento) {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setFecha(movimiento.getFecha());
        movimientoDTO.setSaldo(movimiento.getSaldo());
        movimientoDTO.setValor(movimiento.getValor());
        movimientoDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
        return movimientoDTO;
    }

    public List<MovimientoDTO> criarMovimientoDTO(List<Movimiento> listMovimientos) {
        List<MovimientoDTO> movimientoDTOs = new ArrayList<>();
        listMovimientos.forEach(movimiento -> movimientoDTOs.add(criarMovimientoDTO(movimiento)));
        return movimientoDTOs;
    }
}
