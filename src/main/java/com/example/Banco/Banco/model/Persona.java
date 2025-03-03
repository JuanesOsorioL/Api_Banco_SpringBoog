package com.example.Banco.Banco.model;

import com.example.Banco.Banco.dto.ClienteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
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


    public Persona(ClienteDTO clienteDTO) {
        this.identificacion = clienteDTO.getIdentificacion();
        this.nombre = clienteDTO.getNombre();
        this.genero = clienteDTO.getGenero();
        this.edad = clienteDTO.getEdad();
        this.direccion = clienteDTO.getDireccion();
        this.telefono = clienteDTO.getTelefono();
    }
}
