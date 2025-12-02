package com.appGastroCol.product_backend.service;

import java.util.List;
import com.appGastroCol.product_backend.dto.ReaccionDTO;
import com.appGastroCol.product_backend.dto.ReaccionResponseDTO;

public interface ReaccionService {
    ReaccionDTO reaccionar(Long publicacionId, Long usuarioId);

    long contarReacciones(Long publicacionId);

    List<ReaccionResponseDTO> listarReacciones(Long publicacionId);

}
