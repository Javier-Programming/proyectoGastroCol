package com.appGastroCol.product_backend.controller;

import com.appGastroCol.product_backend.dto.LoginRequest;
import com.appGastroCol.product_backend.dto.RegisterRequest;
import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        Usuario created = authService.register(request);
        // No devolvemos contraseña
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok().body(new AuthResponse(token));
    }

    // Clase interna simple para respuesta de login
    static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
