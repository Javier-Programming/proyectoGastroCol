package com.appGastroCol.product_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.appGastroCol.product_backend.dto.DTOMapper;
import com.appGastroCol.product_backend.dto.PublicacionDTO;
import com.appGastroCol.product_backend.dto.PublicacionResponseDTO;
import com.appGastroCol.product_backend.entity.Publicacion;
import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.repository.PublicacionRepository;
import com.appGastroCol.product_backend.repository.UsuarioRepository;
import com.appGastroCol.product_backend.service.PublicacionService;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;

    public PublicacionServiceImpl(PublicacionRepository publicacionRepository, UsuarioRepository usuarioRepository) {
        this.publicacionRepository = publicacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public PublicacionResponseDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        if (publicacionDTO == null) {
            throw new IllegalArgumentException("La publicación no puede ser nula");
        }

        Long usuarioId = publicacionDTO.getUsuarioId();
        if (usuarioId == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setPrecioPlato(publicacionDTO.getPrecioPlato());
        publicacion.setImagen(publicacionDTO.getImagen());
        publicacion.setUsuario(usuario);

        Publicacion publicacionGuardada = publicacionRepository.save(publicacion);
        return DTOMapper.toPublicacionResponseDTO(publicacionGuardada);
    }

    @Override
    public List<PublicacionResponseDTO> obtenerTodasPublicaciones() {
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        return publicaciones.stream()
                .map(DTOMapper::toPublicacionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PublicacionResponseDTO obtenerPublicacionPorId(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada con ID: " + id));
        return DTOMapper.toPublicacionResponseDTO(publicacion);
    }

    @Override
    public PublicacionResponseDTO actualizarPublicacion(Long id, PublicacionDTO publicacionDTO) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada con ID: " + id));

        if (publicacionDTO.getTitulo() != null) {
            publicacion.setTitulo(publicacionDTO.getTitulo());
        }
        if (publicacionDTO.getDescripcion() != null) {
            publicacion.setDescripcion(publicacionDTO.getDescripcion());
        }
        if (publicacionDTO.getPrecioPlato() != null) {
            publicacion.setPrecioPlato(publicacionDTO.getPrecioPlato());
        }
        if (publicacionDTO.getImagen() != null) {
            publicacion.setImagen(publicacionDTO.getImagen());
        }

        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
        return DTOMapper.toPublicacionResponseDTO(publicacionActualizada);
    }

    @Override
    public void eliminarPublicacion(Long id) {
        if (!publicacionRepository.existsById(id)) {
            throw new RuntimeException("Publicación no encontrada con ID: " + id);
        }
        publicacionRepository.deleteById(id);
    }

    @Override
    public List<PublicacionResponseDTO> obtenerPublicacionesPorUsuario(Long usuarioId) {
        List<Publicacion> publicaciones = publicacionRepository.findByUsuarioId(usuarioId);
        return publicaciones.stream()
                .map(DTOMapper::toPublicacionResponseDTO)
                .collect(Collectors.toList());
    }
}
