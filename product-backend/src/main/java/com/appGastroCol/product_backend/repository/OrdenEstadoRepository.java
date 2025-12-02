package com.appGastroCol.product_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.OrdenEstado;

@Repository
public interface OrdenEstadoRepository extends JpaRepository<OrdenEstado, Integer> {
    List<OrdenEstado> findByOrdenIdOrderByFechaCambioAsc(Integer ordenId);
}
