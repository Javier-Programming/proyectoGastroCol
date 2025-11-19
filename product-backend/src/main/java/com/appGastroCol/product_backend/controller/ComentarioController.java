package com.appGastroCol.product_backend.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.appGastroCol.product_backend.dto.ComentarioDTO;
import com.appGastroCol.product_backend.dto.ComentarioResponseDTO;
import com.appGastroCol.product_backend.service.ComentarioService;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    // Inyección de dependencia del servicio
    private final ComentarioService comentarioService;

    // Constructor
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    // Crear comentario - Recibe ComentarioDTO, retorna ComentarioResponseDTO
    @PostMapping
    public ResponseEntity<ComentarioResponseDTO> crear(@RequestBody ComentarioDTO comentarioDTO) {
        ComentarioResponseDTO comentarioCreado = comentarioService.crearComentario(comentarioDTO);
        return ResponseEntity.ok(comentarioCreado);
    }

    // Comentarios de un post - Retorna lista de ComentarioResponseDTO
    @GetMapping("/post/{publicacionId}")
    public ResponseEntity<List<ComentarioResponseDTO>> obtenerPorPublicacion(@PathVariable Long publicacionId) {
        List<ComentarioResponseDTO> comentarios = comentarioService.obtenerComentariosPorPublicacion(publicacionId);
        return ResponseEntity.ok(comentarios);
    }

    // Eliminar comentario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }
}