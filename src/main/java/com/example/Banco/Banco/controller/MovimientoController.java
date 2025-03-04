package com.example.Banco.Banco.controller;

import com.example.Banco.Banco.dto.MovimientoDTO;
import com.example.Banco.Banco.model.ApiResponse;
import com.example.Banco.Banco.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banco/movimiento")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MovimientoDTO>> save(@RequestBody MovimientoDTO movimientoDTO) {
        return movimientoService.save(movimientoDTO)
                .map(movimientoDTOSaved -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Movimiento creada exitosamente", movimientoDTOSaved)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ApiResponse<>(HttpStatus.CONFLICT.value(), "Cuenta Ya Existe", null)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MovimientoDTO>>> getAll() {
        List<MovimientoDTO> listMovimientoDTO = movimientoService.findAll();
        return listMovimientoDTO.isEmpty()
                ? ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "No hay Movimientos registrados", null))
                : ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Lista de Movimientos", listMovimientoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MovimientoDTO>> getMovimientoDTOById(@PathVariable Long id) {
        return movimientoService.findByMovimientoId(id)
                .map(movimientoDTO -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Movimientos Encontrado", movimientoDTO)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "movimientos No Existe", null)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<MovimientoDTO>> updateMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        return movimientoService.update(movimientoDTO)
                .map(existAccountDTO -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Movimientos Actualizado", existAccountDTO)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Movimientos No Existe", null)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<MovimientoDTO>> deleteMovimientoById(@PathVariable Long id) {
        return movimientoService.delete(id)
                .map(movimientoDTO -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Movimientos Eliminado", movimientoDTO)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Movimientos No Existe", null)));
    }

}
