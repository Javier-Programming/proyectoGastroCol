package com.appGastroCol.product_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Métodos personalizados.
    // Por ejemplo: List<Rol> findByNombre(String nombre);
}
