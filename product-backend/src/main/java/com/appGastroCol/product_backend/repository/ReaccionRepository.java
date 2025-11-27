package com.appGastroCol.product_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.Reaccion;

@Repository
public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {
    // Métodos personalizados.
    List<Reaccion> findByPublicacionId(Long publicacionId); // Obtener todas las reacciones de una publicación
    
    boolean existsByUsuarioIdAndPublicacionId(Long usuarioId, Long publicacionId); // Verificar si el usuario ya reaccionó
    
    void deleteByUsuarioIdAndPublicacionId(Long usuarioId, Long publicacionId); // Eliminar reacción de un usuario a una publicación
}
