package com.appGastroCol.product_backend.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appGastroCol.product_backend.dto.OrdenDTO;
import com.appGastroCol.product_backend.entity.OrdenarPlato;
import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.entity.Publicacion;
import com.appGastroCol.product_backend.repository.OrdenRepository;
import com.appGastroCol.product_backend.repository.UsuarioRepository;
import com.appGastroCol.product_backend.repository.PublicacionRepository;
import com.appGastroCol.product_backend.service.OrdenService;
import com.appGastroCol.product_backend.exception.RecursoNoEncontradoException;
import com.appGastroCol.product_backend.entity.OrdenEstado;
import com.appGastroCol.product_backend.dto.OrdenEstadoDTO;
import com.appGastroCol.product_backend.dto.DTOMapper;
import com.appGastroCol.product_backend.repository.OrdenEstadoRepository;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private OrdenEstadoRepository ordenEstadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public OrdenarPlato crearOrden(OrdenDTO ordenDTO) {
        Usuario consumidor = usuarioRepository.findById(ordenDTO.getConsumidorId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Consumidor no encontrado"));

        Usuario restaurante = usuarioRepository.findById(ordenDTO.getRestauranteId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Restaurante no encontrado"));

        Publicacion publicacion = publicacionRepository.findById(ordenDTO.getPublicacionId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Publicación no encontrada"));

        OrdenarPlato orden = new OrdenarPlato();
        orden.setConsumidor(consumidor);
        orden.setRestaurante(restaurante);
        orden.setPublicacion(publicacion);
        orden.setCantidad(ordenDTO.getCantidad());
        // Estado y fecha se manejan en @PrePersist o por defecto

        OrdenarPlato guardada = ordenRepository.save(orden);

        // Guardar estado inicial en historial
        OrdenEstado inicial = new OrdenEstado();
        inicial.setOrden(guardada);
        inicial.setEstado(guardada.getEstado());
        inicial.setFechaCambio(LocalDateTime.now());
        ordenEstadoRepository.save(inicial);

        return guardada;
    }

    @Override
    public Optional<OrdenarPlato> obtenerOrden(Integer id) {
        return ordenRepository.findById(id);
    }

    @Override
    public List<OrdenarPlato> listarOrdenesPorConsumidor(Long consumidorId) {
        return ordenRepository.findByConsumidorId(consumidorId);
    }

    @Override
    public List<OrdenarPlato> listarOrdenesPorRestaurante(Long restauranteId) {
        return ordenRepository.findByRestauranteId(restauranteId);
    }

    @Override
    public OrdenarPlato actualizarEstado(Integer id, OrdenarPlato.EstadoOrden nuevoEstado) {
        OrdenarPlato orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden no encontrada"));
        orden.setEstado(nuevoEstado);
        OrdenarPlato actualizada = ordenRepository.save(orden);

        // Guardar cambio de estado en historial
        OrdenEstado cambio = new OrdenEstado();
        cambio.setOrden(actualizada);
        cambio.setEstado(nuevoEstado);
        cambio.setFechaCambio(LocalDateTime.now());
        ordenEstadoRepository.save(cambio);

        return actualizada;
    }

    @Override
    public List<OrdenEstadoDTO> obtenerHistorial(Integer ordenId) {
        // Validar existencia de orden
        ordenRepository.findById(ordenId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden no encontrada"));

        return ordenEstadoRepository.findByOrdenIdOrderByFechaCambioAsc(ordenId).stream()
                .map(DTOMapper::toOrdenEstadoDTO)
                .collect(Collectors.toList());
    }
}
