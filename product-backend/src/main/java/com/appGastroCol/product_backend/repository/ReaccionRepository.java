package com.appGastroCol.product_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.Reaccion;

import java.util.List;

@Repository
public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {
    // Buscar reacción por usuario y publicación
    java.util.Optional<Reaccion> findByUsuarioIdAndPublicacionId(Long usuarioId, Long publicacionId);

    // Contar las reacciones de una publicación
    long countByPublicacionId(Long publicacionId);

    // Obtener todas las reacciones de una publicación
    List<Reaccion> findAllByPublicacionId(Long publicacionId);
}
