package com.appGastroCol.product_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appGastroCol.product_backend.entity.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    // Métodos personalizados.
    // Por ejemplo: List<Publicacion> findByTituloContaining(String titulo);
    List<Publicacion> findByUsuarioId(Long usuarioId);
}