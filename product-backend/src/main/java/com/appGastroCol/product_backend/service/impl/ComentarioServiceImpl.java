package com.appGastroCol.product_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.appGastroCol.product_backend.dto.ComentarioDTO;
import com.appGastroCol.product_backend.dto.ComentarioResponseDTO;
import com.appGastroCol.product_backend.dto.DTOMapper;
import com.appGastroCol.product_backend.entity.Comentario;
import com.appGastroCol.product_backend.entity.Publicacion;
import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.repository.ComentarioRepository;
import com.appGastroCol.product_backend.repository.PublicacionRepository;
import com.appGastroCol.product_backend.repository.UsuarioRepository;
import com.appGastroCol.product_backend.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final PublicacionRepository publicacionRepository;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository,
            UsuarioRepository usuarioRepository,
            PublicacionRepository publicacionRepository) {
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
    }

    @Override
    public ComentarioResponseDTO crearComentario(ComentarioDTO comentarioDTO) {

        if (comentarioDTO == null) {
            throw new IllegalArgumentException("El comentario no puede ser nulo");
        }

        // *** Esta es la parte que elimina la advertencia ***
        Long usuarioId = comentarioDTO.getUsuarioId();
        if (usuarioId == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }

        Long publicacionId = comentarioDTO.getPublicacionId();
        if (publicacionId == null) {
            throw new IllegalArgumentException("El ID de la publicación no puede ser nulo");
        }

        // Buscar usuario
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        // Buscar publicación
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada con ID: " + publicacionId));

        // Crear comentario
        Comentario comentario = new Comentario();
        comentario.setContenido(comentarioDTO.getContenido());
        comentario.setUsuario(usuario);
        comentario.setPublicacion(publicacion);

        Comentario comentarioGuardado = comentarioRepository.save(comentario);
        return DTOMapper.toComentarioResponseDTO(comentarioGuardado);
    }

    @Override
    public List<ComentarioResponseDTO> obtenerComentariosPorPublicacion(Long publicacionId) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);

        // Convertir lista de entidades a lista de DTOs
        return comentarios.stream()
                .map(DTOMapper::toComentarioResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarComentario(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        if (!comentarioRepository.existsById(id)) {
            throw new RuntimeException("Comentario no encontrado con ID: " + id);
        }

        comentarioRepository.deleteById(id);
    }
}