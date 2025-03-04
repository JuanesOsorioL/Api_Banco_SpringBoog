package com.example.Banco.Banco.Component;

import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.model.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CuentaMapper {

    @Mapping(source = "cliente.clienteId", target = "clienteId")
    CuentaDTO toDTO(Cuenta cuenta);

    @Mapping(source = "clienteId", target = "cliente.clienteId")
    Cuenta toEntity(CuentaDTO cuentaDTO);

    List<CuentaDTO> toDTOList(List<Cuenta> cuentas);

}

