package com.example.Banco.Banco.dto;

import com.example.Banco.Banco.model.Cliente;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {

    private Long identificacion;
    private String nombre;
    private String genero;
    private Integer edad;
    private String direccion;
    private String telefono;


    public PersonaDTO(Cliente cliente) {
        this.identificacion = cliente.getIdentificacion();
        this.nombre = cliente.getNombre();
        this.genero = cliente.getGenero();
        this.edad = cliente.getEdad();
        this.direccion = cliente.getDireccion();
        this.telefono = cliente.getTelefono();
    }

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "identificacion=" + identificacion +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", edad=" + edad +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
