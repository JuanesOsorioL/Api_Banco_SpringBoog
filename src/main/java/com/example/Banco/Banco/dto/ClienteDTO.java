package com.example.Banco.Banco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private String nombre;
    private String genero;
    private Integer edad;
    private Long identificacion;
    private String direccion;
    private String telefono;
    private Long clienteId;
    private Boolean estado;

}