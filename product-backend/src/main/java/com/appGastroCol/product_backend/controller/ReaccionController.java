package com.appGastroCol.product_backend.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.appGastroCol.product_backend.dto.ReaccionDTO;
import com.appGastroCol.product_backend.dto.ReaccionResponseDTO;
import com.appGastroCol.product_backend.service.ReaccionService;

@RestController
@RequestMapping("/api/reacciones")
public class ReaccionController {

    // Inyección de dependencia del servicio
    private final ReaccionService reaccionService;

    // Constructor
    public ReaccionController(ReaccionService reaccionService) {
        this.reaccionService = reaccionService;
    }

    // Crear reacción - Recibe ReaccionDTO, retorna ReaccionResponseDTO
    @PostMapping
    public ResponseEntity<ReaccionResponseDTO> crear(@RequestBody ReaccionDTO reaccionDTO) {
        ReaccionResponseDTO reaccionCreada = reaccionService.crearReaccion(reaccionDTO);
        return ResponseEntity.ok(reaccionCreada);
    }

    // Obtener reacciones de una publicación
    @GetMapping("/publicacion/{publicacionId}")
    public ResponseEntity<List<ReaccionResponseDTO>> obtenerPorPublicacion(@PathVariable Long publicacionId) {
        List<ReaccionResponseDTO> reacciones = reaccionService.obtenerReaccionesPorPublicacion(publicacionId);
        return ResponseEntity.ok(reacciones);
    }

    // Verificar si un usuario ya reaccionó a una publicación
    @GetMapping("/verificar/{usuarioId}/{publicacionId}")
    public ResponseEntity<Boolean> verificarReaccion(@PathVariable Long usuarioId, @PathVariable Long publicacionId) {
        boolean existe = reaccionService.verificarReaccionExistente(usuarioId, publicacionId);
        return ResponseEntity.ok(existe);
    }

    // Eliminar reacción por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reaccionService.eliminarReaccion(id);
        return ResponseEntity.noContent().build();
    }

    // Eliminar reacción de un usuario a una publicación específica
    @DeleteMapping("/usuario/{usuarioId}/publicacion/{publicacionId}")
    public ResponseEntity<Void> eliminarPorUsuarioYPublicacion(@PathVariable Long usuarioId, @PathVariable Long publicacionId) {
        reaccionService.eliminarReaccionPorUsuarioYPublicacion(usuarioId, publicacionId);
        return ResponseEntity.noContent().build();
    }
}
