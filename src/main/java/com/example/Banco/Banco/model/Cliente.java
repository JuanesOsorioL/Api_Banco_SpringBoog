package com.example.Banco.Banco.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
public class Cliente extends Persona {
    @Column(unique = true, nullable = false)
    private String clienteId;

    private String contrasena;
    private Boolean estado;
}
