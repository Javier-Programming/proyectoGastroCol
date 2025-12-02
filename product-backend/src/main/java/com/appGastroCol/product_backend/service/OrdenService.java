package com.appGastroCol.product_backend.service;

import java.util.List;
import java.util.Optional;
import com.appGastroCol.product_backend.dto.OrdenDTO;
import com.appGastroCol.product_backend.dto.OrdenEstadoDTO;
import com.appGastroCol.product_backend.entity.OrdenarPlato;

public interface OrdenService {
    OrdenarPlato crearOrden(OrdenDTO ordenDTO);

    Optional<OrdenarPlato> obtenerOrden(Integer id);

    List<OrdenarPlato> listarOrdenesPorConsumidor(Long consumidorId);

    List<OrdenarPlato> listarOrdenesPorRestaurante(Long restauranteId);

    OrdenarPlato actualizarEstado(Integer id, OrdenarPlato.EstadoOrden nuevoEstado);

    // Obtener historial de estados para una orden
    List<OrdenEstadoDTO> obtenerHistorial(Integer ordenId);
}
