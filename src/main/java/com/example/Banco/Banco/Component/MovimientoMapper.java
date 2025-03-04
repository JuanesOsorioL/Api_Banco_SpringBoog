package com.example.Banco.Banco.Component;

import com.example.Banco.Banco.dto.MovimientoDTO;
import com.example.Banco.Banco.model.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    @Mapping(source = "cuenta.numeroCuenta", target = "numeroCuenta")
    MovimientoDTO toDTO(Movimiento movimiento);

    @Mapping(source = "numeroCuenta", target = "cuenta.numeroCuenta")
    Movimiento toEntity(MovimientoDTO movimientoDTO);

    List<MovimientoDTO> toDTOList(List<Movimiento> movimiento);
}