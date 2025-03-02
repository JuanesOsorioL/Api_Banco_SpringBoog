package com.example.Banco.Banco.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    private Long identificacion;
    private String nombre;
    private String genero;
    private Integer edad;
    private String direccion;
    private String telefono;
}
