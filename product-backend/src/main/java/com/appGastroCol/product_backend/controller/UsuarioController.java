package com.appGastroCol.product_backend.controller;

import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        Usuario u = usuarioService.findById(id);
        u.setContrasena(null); // No exponer contraseña
        return ResponseEntity.ok(u);
    }
}
