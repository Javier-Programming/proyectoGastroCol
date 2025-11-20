package com.appGastroCol.product_backend.service;

import java.util.Optional;
import com.appGastroCol.product_backend.entity.Usuario;

public interface UsuarioService {
    Optional<Usuario> findByCorreo(String correo);

    Usuario save(Usuario usuario);

    Usuario findById(Long id);
}
