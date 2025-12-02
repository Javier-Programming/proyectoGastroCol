package com.appGastroCol.product_backend.service;

import com.appGastroCol.product_backend.dto.ReaccionDTO;
import com.appGastroCol.product_backend.entity.Publicacion;
import com.appGastroCol.product_backend.entity.Reaccion;
import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.repository.PublicacionRepository;
import com.appGastroCol.product_backend.repository.ReaccionRepository;
import com.appGastroCol.product_backend.repository.UsuarioRepository;
import com.appGastroCol.product_backend.service.impl.ReaccionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReaccionServiceTest {

    @Mock
    private ReaccionRepository reaccionRepository;

    @Mock
    private PublicacionRepository publicacionRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ReaccionServiceImpl reaccionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReaccionar_Like() {
        Long usuarioId = 1L;
        Long publicacionId = 1L;

        when(reaccionRepository.findByUsuarioIdAndPublicacionId(usuarioId, publicacionId)).thenReturn(Optional.empty());

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        Publicacion publicacion = new Publicacion();
        publicacion.setId(publicacionId);
        when(publicacionRepository.findById(publicacionId)).thenReturn(Optional.of(publicacion));

        Reaccion savedReaccion = new Reaccion();
        savedReaccion.setId(100L);
        savedReaccion.setUsuario(usuario);
        savedReaccion.setPublicacion(publicacion);
        savedReaccion.setFechaCreacion(LocalDateTime.now());

        when(reaccionRepository.save(any(Reaccion.class))).thenReturn(savedReaccion);

        ReaccionDTO result = reaccionService.reaccionar(publicacionId, usuarioId);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(usuarioId, result.getUsuarioId());
        assertEquals(publicacionId, result.getPublicacionId());
        verify(reaccionRepository, times(1)).save(any(Reaccion.class));
    }

    @Test
    void testReaccionar_Unlike() {
        Long usuarioId = 1L;
        Long publicacionId = 1L;

        Reaccion existingReaccion = new Reaccion();
        existingReaccion.setId(100L);

        when(reaccionRepository.findByUsuarioIdAndPublicacionId(usuarioId, publicacionId))
                .thenReturn(Optional.of(existingReaccion));

        ReaccionDTO result = reaccionService.reaccionar(publicacionId, usuarioId);

        assertNull(result);
        verify(reaccionRepository, times(1)).delete(existingReaccion);
    }

    @Test
    void testContarReacciones() {
        Long publicacionId = 1L;
        when(reaccionRepository.countByPublicacionId(publicacionId)).thenReturn(5L);

        long count = reaccionService.contarReacciones(publicacionId);

        assertEquals(5L, count);
    }
}
