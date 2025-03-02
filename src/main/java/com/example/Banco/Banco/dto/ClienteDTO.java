package com.example.Banco.Banco.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClienteDTO extends PersonaDTO {
    private String clienteId;
    private String contrasena;
    private Boolean estado;
}
