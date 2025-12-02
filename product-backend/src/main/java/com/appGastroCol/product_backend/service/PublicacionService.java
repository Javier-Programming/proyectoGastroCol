package com.appGastroCol.product_backend.service;

import java.util.List;
import com.appGastroCol.product_backend.dto.PublicacionDTO;
import com.appGastroCol.product_backend.dto.PublicacionResponseDTO;

public interface PublicacionService {
    PublicacionResponseDTO crearPublicacion(PublicacionDTO publicacionDTO);

    List<PublicacionResponseDTO> obtenerTodasPublicaciones();

    PublicacionResponseDTO obtenerPublicacionPorId(Long id);

    PublicacionResponseDTO actualizarPublicacion(Long id, PublicacionDTO publicacionDTO);

    void eliminarPublicacion(Long id);

    List<PublicacionResponseDTO> obtenerPublicacionesPorUsuario(Long usuarioId);
}
