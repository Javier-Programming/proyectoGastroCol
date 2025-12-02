package com.appGastroCol.product_backend.service.impl;

import java.util.List;

import com.appGastroCol.product_backend.dto.ReaccionDTO;
import com.appGastroCol.product_backend.dto.ReaccionResponseDTO;
import com.appGastroCol.product_backend.entity.Publicacion;
import com.appGastroCol.product_backend.entity.Reaccion;
import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.repository.PublicacionRepository;
import com.appGastroCol.product_backend.repository.ReaccionRepository;
import com.appGastroCol.product_backend.repository.UsuarioRepository;
import com.appGastroCol.product_backend.service.ReaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReaccionServiceImpl implements ReaccionService {

    @Autowired
    private ReaccionRepository reaccionRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public ReaccionDTO reaccionar(Long publicacionId, Long usuarioId) {
        Optional<Reaccion> existingReaccion = reaccionRepository.findByUsuarioIdAndPublicacionId(usuarioId,
                publicacionId);

        if (existingReaccion.isPresent()) {
            // Si ya existe, la eliminamos (Unlike)
            reaccionRepository.delete(existingReaccion.get());
            return null; // O retornar un DTO indicando que se eliminó
        } else {
            // Si no existe, la creamos (Like)
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Publicacion publicacion = publicacionRepository.findById(publicacionId)
                    .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

            Reaccion reaccion = new Reaccion();
            reaccion.setUsuario(usuario);
            reaccion.setPublicacion(publicacion);

            Reaccion savedReaccion = reaccionRepository.save(reaccion);
            return new ReaccionDTO(
                    savedReaccion.getId(),
                    savedReaccion.getUsuario().getId(),
                    savedReaccion.getPublicacion().getId(),
                    savedReaccion.getFechaCreacion());
        }
    }

    @Override
    public List<ReaccionResponseDTO> listarReacciones(Long publicacionId) {

        List<Reaccion> reacciones = reaccionRepository.findAllByPublicacionId(publicacionId);

        return reacciones.stream().map(reaccion -> {
            Usuario usuario = reaccion.getUsuario();
            return new ReaccionResponseDTO(
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getImagen());
        }).toList();
    }

    @Override
    public long contarReacciones(Long publicacionId) {
        return reaccionRepository.countByPublicacionId(publicacionId);
    }
}
