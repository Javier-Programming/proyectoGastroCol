package com.appGastroCol.product_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appGastroCol.product_backend.dto.PublicacionDTO;
import com.appGastroCol.product_backend.dto.PublicacionResponseDTO;
import com.appGastroCol.product_backend.service.PublicacionService;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @PostMapping
    public ResponseEntity<PublicacionResponseDTO> crearPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
        PublicacionResponseDTO nuevaPublicacion = publicacionService.crearPublicacion(publicacionDTO);
        return new ResponseEntity<>(nuevaPublicacion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PublicacionResponseDTO>> obtenerTodasPublicaciones() {
        List<PublicacionResponseDTO> publicaciones = publicacionService.obtenerTodasPublicaciones();
        return ResponseEntity.ok(publicaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionResponseDTO> obtenerPublicacionPorId(@PathVariable Long id) {
        PublicacionResponseDTO publicacion = publicacionService.obtenerPublicacionPorId(id);
        return ResponseEntity.ok(publicacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionResponseDTO> actualizarPublicacion(@PathVariable Long id,
            @RequestBody PublicacionDTO publicacionDTO) {
        PublicacionResponseDTO publicacionActualizada = publicacionService.actualizarPublicacion(id, publicacionDTO);
        return ResponseEntity.ok(publicacionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long id) {
        publicacionService.eliminarPublicacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PublicacionResponseDTO>> obtenerPublicacionesPorUsuario(@PathVariable Long usuarioId) {
        List<PublicacionResponseDTO> publicaciones = publicacionService.obtenerPublicacionesPorUsuario(usuarioId);
        return ResponseEntity.ok(publicaciones);
    }
}
