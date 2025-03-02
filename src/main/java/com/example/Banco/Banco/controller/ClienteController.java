package com.example.Banco.Banco.controller;

import com.example.Banco.Banco.dto.ClienteDTO;
import com.example.Banco.Banco.model.ApiResponse;
import com.example.Banco.Banco.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banco/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteDTO>> saveCliente(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.save(clienteDTO)
                .map(clientSaved -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Cliente creado exitosamente", clientSaved)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ApiResponse<>(HttpStatus.CONFLICT.value(), "Cliente Ya Existe", null)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClienteDTO>>> getAllClientes() {
        List<ClienteDTO> listClientDTO = clienteService.findAll();
        return listClientDTO.isEmpty()
                ? ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "No hay clientes registrados", null))
                : ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Lista de Clientes", listClientDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteDTO>> getClienteById(@PathVariable Long id) {
        return clienteService.findByIdentificacion(id)
                .map(clientDTO -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Cliente encontrado", clientDTO)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Cliente No Existe", null)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteDTO>> deleteClienteById(@PathVariable Long id) {
        return clienteService.delete(id)
                .map(clientDTO -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Cliente Eliminado", clientDTO)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Cliente No Existe", null)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ClienteDTO>> updateCliente(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.update(clienteDTO)
                .map(existClientDTO -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Cliente Actualizado", existClientDTO)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Cliente No Existe", null)));
    }
}
