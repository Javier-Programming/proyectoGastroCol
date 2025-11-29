package com.appGastroCol.product_backend.service;

import java.util.List;
import com.appGastroCol.product_backend.dto.ReaccionDTO;
import com.appGastroCol.product_backend.dto.ReaccionResponseDTO;

public interface ReaccionService {
    ReaccionResponseDTO crearReaccion(ReaccionDTO reaccionDTO);

    List<ReaccionResponseDTO> obtenerReaccionesPorPublicacion(Long publicacionId);

    void eliminarReaccion(Long id);

    void eliminarReaccionPorUsuarioYPublicacion(Long usuarioId, Long publicacionId);

    boolean verificarReaccionExistente(Long usuarioId, Long publicacionId);
}
