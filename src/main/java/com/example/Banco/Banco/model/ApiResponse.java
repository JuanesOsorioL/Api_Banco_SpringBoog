package com.example.Banco.Banco.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private Integer status;
    private String mensaje; // Mensaje de éxito o error
    private T body; // Cuerpo de la respuesta, puede ser cualquier objeto
}
