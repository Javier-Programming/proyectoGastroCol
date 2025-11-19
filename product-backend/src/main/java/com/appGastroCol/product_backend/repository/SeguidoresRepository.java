package com.appGastroCol.product_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.Seguidor;

@Repository
public interface SeguidoresRepository extends JpaRepository<Seguidor, Long> {
    // Métodos personalizados.
    // Por ejemplo: List<Seguidores> findByUsuarioId(Long usuarioId);
}
