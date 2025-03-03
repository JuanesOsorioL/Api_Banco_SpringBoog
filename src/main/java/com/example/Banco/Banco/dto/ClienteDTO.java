package com.example.Banco.Banco.dto;

import com.example.Banco.Banco.model.Cliente;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClienteDTO extends PersonaDTO {
    private Long clienteId;
    private String contrasena;
    private Boolean estado;
    private List<CuentaDTO> cuentas;

    public ClienteDTO(Cliente cliente) {
        super(cliente);
        this.clienteId = cliente.getIdentificacion();
        this.contrasena = cliente.getContrasena();
        this.estado = cliente.getEstado();
        this.cuentas = cliente.getCuentas().stream()
                .map(CuentaDTO::new)
                .collect(Collectors.toList());
    }
}
