package com.appGastroCol.product_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.Reaccion;

@Repository
public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {
    // Métodos personalizados.
    // Por ejemplo: List<Reaccion> findByTipo(String tipo);
}
