package com.appGastroCol.product_backend.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Métodos personalizados.
    // Por ejemplo: List<Usuario> findByEmail(String email);
    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}
