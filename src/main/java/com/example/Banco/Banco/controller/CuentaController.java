package com.example.Banco.Banco.controller;

import com.example.Banco.Banco.dto.CuentaDTO;
import com.example.Banco.Banco.model.ApiResponse;
import com.example.Banco.Banco.service.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banco/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CuentaDTO>> saveAccount(@RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.save(cuentaDTO)
                .map(accountSaved -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Cuenta creada exitosamente", accountSaved)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ApiResponse<>(HttpStatus.CONFLICT.value(), "Cuenta Ya Existe", null)));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<CuentaDTO>>> getAllAccounts() {
        List<CuentaDTO> listCuentaDTO = cuentaService.findAll();
        return listCuentaDTO.isEmpty()
                ? ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "No hay Cuenta registrados", null))
                : ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Lista de Cuentas", listCuentaDTO));
    }


    @GetMapping("/{account}")
    public ResponseEntity<ApiResponse<CuentaDTO>> getCuentaByAccount(@PathVariable Long account) {
        return cuentaService.findByNumeroCuenta(account)
                .map(cuentaDTO -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Cuenta Encontrado", cuentaDTO)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "cuenta No Existe", null)));
    }

    @DeleteMapping("/{account}")
    public ResponseEntity<ApiResponse<CuentaDTO>> deleteAccountById(@PathVariable Long account) {
        return cuentaService.delete(account)
                .map(cuentaDTO -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Cuenta Eliminado", cuentaDTO)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Cuenta No Existe", null)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CuentaDTO>> updateAccount(@RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.update(cuentaDTO)
                .map(existAccountDTO -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Cuenta Actualizado", existAccountDTO)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Cuenta No Existe", null)));
    }

}
