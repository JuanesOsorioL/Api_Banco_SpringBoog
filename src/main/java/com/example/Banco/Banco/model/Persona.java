package com.example.Banco.Banco.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Persona {

    private String nombre;
    private String genero;
    private Integer edad;
    private Long identificacion;
    private String direccion;
    private String telefono;
}