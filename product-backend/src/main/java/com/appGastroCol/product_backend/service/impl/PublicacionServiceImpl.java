package com.appGastroCol.product_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.appGastroCol.product_backend.dto.DTOMapper;
import com.appGastroCol.product_backend.dto.PublicacionDTO;
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
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
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
        return DTOMapper.toPublicacionDTO(publicacionGuardada);
    }

    @Override
    public List<PublicacionDTO> obtenerTodas() {
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        return publicaciones.stream()
                .map(DTOMapper::toPublicacionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PublicacionDTO obtenerPorId(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada con ID: " + id));
        return DTOMapper.toPublicacionDTO(publicacion);
    }

    @Override
    public void eliminarPublicacion(Long id) {
        if (!publicacionRepository.existsById(id)) {
            throw new RuntimeException("Publicación no encontrada con ID: " + id);
        }
        publicacionRepository.deleteById(id);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(Long id, PublicacionDTO publicacionDTO) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada con ID: " + id));

        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setPrecioPlato(publicacionDTO.getPrecioPlato());
        publicacion.setImagen(publicacionDTO.getImagen());

        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
        return DTOMapper.toPublicacionDTO(publicacionActualizada);
    }

    @Override
    public List<PublicacionDTO> obtenerPorUsuario(Long usuarioId) {
        List<Publicacion> publicaciones = publicacionRepository.findByUsuarioId(usuarioId);

        return publicaciones.stream()
                .map(DTOMapper::toPublicacionDTO)
                .collect(Collectors.toList());
    }

}
