package com.appGastroCol.product_backend.service;

import java.util.List;
import com.appGastroCol.product_backend.dto.ComentarioDTO;
import com.appGastroCol.product_backend.dto.ComentarioResponseDTO;

public interface ComentarioService {
    ComentarioResponseDTO crearComentario(ComentarioDTO comentarioDTO);

    List<ComentarioResponseDTO> obtenerComentariosPorPublicacion(Long publicacionId);

    void eliminarComentario(Long id);
}
