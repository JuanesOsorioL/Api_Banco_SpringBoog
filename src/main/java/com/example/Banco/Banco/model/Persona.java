package com.example.Banco.Banco.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Persona {
    @Id
    @Column(unique = true, nullable = false)
    private Long identificacion;

    private String nombre;
    private String genero;
    private Integer edad;

    private String direccion;
    private String telefono;

}
