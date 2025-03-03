package com.example.Banco.Banco.dto;

import com.example.Banco.Banco.model.Cliente;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClienteDTO extends PersonaDTO {
    private Long clienteId;
    private String contrasena;
    private Boolean estado;
    private List<CuentaDTO> cuentas; // Lista de cuentas asociadas

    public ClienteDTO(Cliente cliente) {
        super(cliente);
        this.clienteId = cliente.getIdentificacion();
        this.contrasena = cliente.getContrasena();
        this.estado = cliente.getEstado();
        this.cuentas = new ArrayList<>();
    }

    @Override
    public String toString() {
        return super.toString()+ "ClienteDTO{" +
                "clienteId=" + clienteId +
                ", contrasena='" + contrasena + '\'' +
                ", estado=" + estado +
                ", cuentas=" + cuentas +
                '}';
    }


}
