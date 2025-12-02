package com.appGastroCol.product_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.OrdenarPlato;

@Repository
public interface OrdenRepository extends JpaRepository<OrdenarPlato, Integer> {
    // Buscar ordenes por consumidor
    List<OrdenarPlato> findByConsumidorId(Long consumidorId);

    // Buscar ordenes por restaurante
    List<OrdenarPlato> findByRestauranteId(Long restauranteId);
}