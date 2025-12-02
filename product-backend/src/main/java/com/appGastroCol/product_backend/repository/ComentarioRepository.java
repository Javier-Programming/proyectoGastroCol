package com.appGastroCol.product_backend.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    // Métodos personalizados.
    // Obtener todos los comentarios de un post por ID de post
    List<Comentario> findByPublicacionId(Long publicacionId); 
}
