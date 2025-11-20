package com.appGastroCol.product_backend.service;

import com.appGastroCol.product_backend.dto.LoginRequest;
import com.appGastroCol.product_backend.dto.RegisterRequest;
import com.appGastroCol.product_backend.entity.Usuario;

public interface AuthService {
    String login(LoginRequest request); // Retorna JWT

    Usuario register(RegisterRequest request);
}
