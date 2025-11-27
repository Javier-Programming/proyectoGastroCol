package com.appGastroCol.product_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.Seguidor;

@Repository
public interface SeguidoresRepository extends JpaRepository<Seguidor, Long> {
    // Métodos personalizados.
    List<Seguidor> findBySeguidoId(Long seguidoId); // Obtener todos los seguidores de un usuario

    List<Seguidor> findBySeguidorId(Long seguidorId); // Obtener todos los usuarios que sigue un usuario

    boolean existsBySeguidorIdAndSeguidoId(Long seguidorId, Long seguidoId); // Verificar si un usuario sigue a otro

    void deleteBySeguidorIdAndSeguidoId(Long seguidorId, Long seguidoId); // Dejar de seguir a un usuario

    long countBySeguidoId(Long seguidoId); // Contar seguidores de un usuario

    long countBySeguidorId(Long seguidorId); // Contar cuántos usuarios sigue un usuario
}
