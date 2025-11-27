package com.appGastroCol.product_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appGastroCol.product_backend.dto.PublicacionDTO;
import com.appGastroCol.product_backend.service.PublicacionService;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    // Crear publicacion
    @PostMapping("/crear")
    public ResponseEntity<PublicacionDTO> crear(@RequestBody PublicacionDTO publicacionDTO) {
        PublicacionDTO publicacionCreada = publicacionService.crearPublicacion(publicacionDTO);
        return ResponseEntity.ok(publicacionCreada);
    }

    // Obtener todas las publicaciones
    @GetMapping("/obtener-todas")
    public ResponseEntity<List<PublicacionDTO>> obtenerTodas() {
        List<PublicacionDTO> publicaciones = publicacionService.obtenerTodas();
        return ResponseEntity.ok(publicaciones);
    }

    // Obtener publicacion por ID
    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPorId(@PathVariable Long id) {
        PublicacionDTO publicacion = publicacionService.obtenerPorId(id);
        return ResponseEntity.ok(publicacion);
    }

    // Obtener publicaciones por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PublicacionDTO>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        List<PublicacionDTO> publicaciones = publicacionService.obtenerPorUsuario(usuarioId);
        return ResponseEntity.ok(publicaciones);
    }

    // Eliminar publicacion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        publicacionService.eliminarPublicacion(id);
        return ResponseEntity.noContent().build();
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizar(@PathVariable Long id,
            @RequestBody PublicacionDTO publicacionDTO) {
        PublicacionDTO publicacionActualizada = publicacionService.actualizarPublicacion(id, publicacionDTO);
        return ResponseEntity.ok(publicacionActualizada);
    }
}
