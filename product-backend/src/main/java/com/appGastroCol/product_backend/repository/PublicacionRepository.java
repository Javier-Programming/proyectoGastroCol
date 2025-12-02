package com.appGastroCol.product_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appGastroCol.product_backend.entity.Publicacion;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    // Buscar publicaciones por usuario
    List<Publicacion> findByUsuarioId(Long usuarioId);
}