package com.appGastroCol.product_backend.controller;

import com.appGastroCol.product_backend.dto.ReaccionDTO;
import com.appGastroCol.product_backend.dto.ReaccionResponseDTO;
import com.appGastroCol.product_backend.service.ReaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reacciones")
public class ReaccionController {

    @Autowired
    private ReaccionService reaccionService;

    @PostMapping("/{publicacionId}")
    public ResponseEntity<?> toggleReaccion(@PathVariable Long publicacionId, @RequestParam Long usuarioId) {
        try {
            ReaccionDTO result = reaccionService.reaccionar(publicacionId, usuarioId);
            if (result == null) {
                return ResponseEntity.ok("Reacción eliminada");
            }
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{publicacionId}/count")
    public ResponseEntity<Long> countReacciones(@PathVariable Long publicacionId) {
        long count = reaccionService.contarReacciones(publicacionId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{publicacionId}/list")
    public ResponseEntity<?> listarReacciones(@PathVariable Long publicacionId) {
        List<ReaccionResponseDTO> lista = reaccionService.listarReacciones(publicacionId);
        return ResponseEntity.ok(lista);
    }

}
