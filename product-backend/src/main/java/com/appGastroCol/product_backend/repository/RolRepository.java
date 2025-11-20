package com.appGastroCol.product_backend.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.Rol;
import com.appGastroCol.product_backend.entity.Rol.TipoRol;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Métodos personalizados.
    // Por ejemplo: List<Rol> findByRol(TipoRol rol);
    Optional<Rol> findByRol(TipoRol rol);
}
