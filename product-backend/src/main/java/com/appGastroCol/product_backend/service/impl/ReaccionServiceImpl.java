package com.appGastroCol.product_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.appGastroCol.product_backend.dto.ReaccionDTO;
import com.appGastroCol.product_backend.dto.ReaccionResponseDTO;
import com.appGastroCol.product_backend.dto.DTOMapper;
import com.appGastroCol.product_backend.entity.Reaccion;
import com.appGastroCol.product_backend.entity.Publicacion;
import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.repository.ReaccionRepository;
import com.appGastroCol.product_backend.repository.PublicacionRepository;
import com.appGastroCol.product_backend.repository.UsuarioRepository;
import com.appGastroCol.product_backend.service.ReaccionService;

@Service
public class ReaccionServiceImpl implements ReaccionService {

    private final ReaccionRepository reaccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final PublicacionRepository publicacionRepository;

    public ReaccionServiceImpl(ReaccionRepository reaccionRepository,
            UsuarioRepository usuarioRepository,
            PublicacionRepository publicacionRepository) {
        this.reaccionRepository = reaccionRepository;
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
    }

    @Override
    public ReaccionResponseDTO crearReaccion(ReaccionDTO reaccionDTO) {

        if (reaccionDTO == null) {
            throw new IllegalArgumentException("La reacción no puede ser nula");
        }

        Long usuarioId = reaccionDTO.getUsuarioId();
        if (usuarioId == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }

        Long publicacionId = reaccionDTO.getPublicacionId();
        if (publicacionId == null) {
            throw new IllegalArgumentException("El ID de la publicación no puede ser nulo");
        }

        // Buscar usuario
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        // Buscar publicación
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada con ID: " + publicacionId));

        // Verificar si el usuario ya reaccionó a esta publicación
        if (reaccionRepository.existsByUsuarioIdAndPublicacionId(usuarioId, publicacionId)) {
            throw new RuntimeException("El usuario ya ha reaccionado a esta publicación");
        }

        // Crear reacción
        Reaccion reaccion = new Reaccion();
        reaccion.setUsuario(usuario);
        reaccion.setPublicacion(publicacion);

        Reaccion reaccionGuardada = reaccionRepository.save(reaccion);
        return DTOMapper.toReaccionResponseDTO(reaccionGuardada);
    }

    @Override
    public List<ReaccionResponseDTO> obtenerReaccionesPorPublicacion(Long publicacionId) {
        List<Reaccion> reacciones = reaccionRepository.findByPublicacionId(publicacionId);

        // Convertir lista de entidades a lista de DTOs
        return reacciones.stream()
                .map(DTOMapper::toReaccionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarReaccion(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        if (!reaccionRepository.existsById(id)) {
            throw new RuntimeException("Reacción no encontrada con ID: " + id);
        }

        reaccionRepository.deleteById(id);
    }

    @Override
    public void eliminarReaccionPorUsuarioYPublicacion(Long usuarioId, Long publicacionId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }

        if (publicacionId == null) {
            throw new IllegalArgumentException("El ID de la publicación no puede ser nulo");
        }

        if (!reaccionRepository.existsByUsuarioIdAndPublicacionId(usuarioId, publicacionId)) {
            throw new RuntimeException("No existe reacción del usuario a esta publicación");
        }

        reaccionRepository.deleteByUsuarioIdAndPublicacionId(usuarioId, publicacionId);
    }

    @Override
    public boolean verificarReaccionExistente(Long usuarioId, Long publicacionId) {
        if (usuarioId == null || publicacionId == null) {
            throw new IllegalArgumentException("Los IDs de usuario y publicación no pueden ser nulos");
        }

        return reaccionRepository.existsByUsuarioIdAndPublicacionId(usuarioId, publicacionId);
    }
}
