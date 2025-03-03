package com.example.Banco.Banco.model;

import com.example.Banco.Banco.dto.ClienteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {

    @Column(unique = true, nullable = false)
    private Long clienteId;

    private String contrasena;
    private Boolean estado;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cuenta> cuentas = new ArrayList<>();

    public Cliente(ClienteDTO clienteDTO) {
        super(clienteDTO);
        this.clienteId = clienteDTO.getIdentificacion();//asigne el mismo identificador
        this.contrasena = clienteDTO.getContrasena();
        this.estado = clienteDTO.getEstado();
       // this.cuentas = clienteDTO.getCuentas();
    }
}
