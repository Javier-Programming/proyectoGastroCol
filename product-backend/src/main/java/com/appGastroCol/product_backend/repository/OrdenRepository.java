package com.appGastroCol.product_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.OrdenarPlato;

@Repository
public interface OrdenRepository extends JpaRepository<OrdenarPlato, Long> {
    // Métodos personalizados.
    // Por ejemplo: List<OrdenarPlato> findByUsuarioId(Long usuarioId);
}