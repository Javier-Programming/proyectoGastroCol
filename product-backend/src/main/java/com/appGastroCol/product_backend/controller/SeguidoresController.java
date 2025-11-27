package com.appGastroCol.product_backend.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.appGastroCol.product_backend.dto.SeguidorDTO;
import com.appGastroCol.product_backend.dto.SeguidorResponseDTO;
import com.appGastroCol.product_backend.dto.UsuarioSimpleDTO;
import com.appGastroCol.product_backend.service.SeguidoresService;

@RestController
@RequestMapping("/api/seguidores")
public class SeguidoresController {

    // Inyección de dependencia del servicio
    private final SeguidoresService seguidoresService;

    // Constructor
    public SeguidoresController(SeguidoresService seguidoresService) {
        this.seguidoresService = seguidoresService;
    }

    // Seguir a un usuario
    @PostMapping
    public ResponseEntity<SeguidorResponseDTO> seguir(@RequestBody SeguidorDTO seguidorDTO) {
        SeguidorResponseDTO nuevoSeguidor = seguidoresService.seguirUsuario(seguidorDTO);
        return ResponseEntity.ok(nuevoSeguidor);
    }

    // Obtener seguidores de un usuario
    @GetMapping("/seguidores/{seguidoId}")
    public ResponseEntity<List<SeguidorResponseDTO>> obtenerSeguidores(@PathVariable Long seguidoId) {
        List<SeguidorResponseDTO> seguidores = seguidoresService.obtenerSeguidores(seguidoId);
        return ResponseEntity.ok(seguidores);
    }

    // Obtener usuarios que sigue un usuario
    @GetMapping("/siguiendo/{seguidorId}")
    public ResponseEntity<List<SeguidorResponseDTO>> obtenerSiguiendo(@PathVariable Long seguidorId) {
        List<SeguidorResponseDTO> siguiendo = seguidoresService.obtenerSiguiendo(seguidorId);
        return ResponseEntity.ok(siguiendo);
    }

    // Verificar si un usuario sigue a otro
    @GetMapping("/verificar/{seguidorId}/{seguidoId}")
    public ResponseEntity<Boolean> verificarSeguimiento(@PathVariable Long seguidorId, @PathVariable Long seguidoId) {
        boolean sigue = seguidoresService.verificarSeguimiento(seguidorId, seguidoId);
        return ResponseEntity.ok(sigue);
    }

    // Contar seguidores de un usuario
    @GetMapping("/contar-seguidores/{seguidoId}")
    public ResponseEntity<Long> contarSeguidores(@PathVariable Long seguidoId) {
        long cantidad = seguidoresService.contarSeguidores(seguidoId);
        return ResponseEntity.ok(cantidad);
    }

    // Contar cuántos usuarios sigue un usuario
    @GetMapping("/contar-siguiendo/{seguidorId}")
    public ResponseEntity<Long> contarSiguiendo(@PathVariable Long seguidorId) {
        long cantidad = seguidoresService.contarSiguiendo(seguidorId);
        return ResponseEntity.ok(cantidad);
    }

    // Obtener lista de usuarios que son seguidores
    @GetMapping("/usuarios-seguidores/{seguidoId}")
    public ResponseEntity<List<UsuarioSimpleDTO>> obtenerUsuariosSeguidores(@PathVariable Long seguidoId) {
        List<UsuarioSimpleDTO> usuarios = seguidoresService.obtenerUsuariosSeguidores(seguidoId);
        return ResponseEntity.ok(usuarios);
    }

    // Obtener lista de usuarios que sigue un usuario
    @GetMapping("/usuarios-siguiendo/{seguidorId}")
    public ResponseEntity<List<UsuarioSimpleDTO>> obtenerUsuariosSiguiendo(@PathVariable Long seguidorId) {
        List<UsuarioSimpleDTO> usuarios = seguidoresService.obtenerUsuariosSiguiendo(seguidorId);
        return ResponseEntity.ok(usuarios);
    }

    // Dejar de seguir por ID del registro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> dejarDeSeguir(@PathVariable Long id) {
        seguidoresService.dejarDeSeguir(id);
        return ResponseEntity.noContent().build();
    }

    // Dejar de seguir especificando IDs de usuario
    @DeleteMapping("/usuarios/{seguidorId}/{seguidoId}")
    public ResponseEntity<Void> dejarDeSeguidoPorIdUsuarios(@PathVariable Long seguidorId, @PathVariable Long seguidoId) {
        seguidoresService.dejarDeSeguidoPorIdUsuarios(seguidorId, seguidoId);
        return ResponseEntity.noContent().build();
    }
}
