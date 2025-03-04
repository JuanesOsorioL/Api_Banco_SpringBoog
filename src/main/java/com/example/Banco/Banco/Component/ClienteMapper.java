package com.example.Banco.Banco.Component;

import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.model.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    //   ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO toDTO(Cliente cliente);

    Cliente toEntity(ClienteDTO clienteDTO);

    List<ClienteDTO> toDTOList(List<Cliente> clientes);

}
