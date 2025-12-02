package com.appGastroCol.product_backend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.appGastroCol.product_backend.dto.OrdenDTO;
import com.appGastroCol.product_backend.dto.OrdenEstadoDTO;
import com.appGastroCol.product_backend.entity.Publicacion;
import com.appGastroCol.product_backend.entity.Rol;
import com.appGastroCol.product_backend.entity.Rol.TipoRol;
import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.repository.PublicacionRepository;
import com.appGastroCol.product_backend.repository.RolRepository;
import com.appGastroCol.product_backend.repository.UsuarioRepository;
import com.appGastroCol.product_backend.service.OrdenService;

@SpringBootTest
@ActiveProfiles("test")
public class OrdenIntegrationTest {

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Test
    public void flujoCrearActualizarHistorial() {
        // Crear roles
        Rol rolConsumidor = rolRepository.findByRol(TipoRol.consumidor).orElseGet(() -> rolRepository.save(new Rol(null, TipoRol.consumidor, "Consumidor")));
        Rol rolRestaurante = rolRepository.findByRol(TipoRol.restaurante).orElseGet(() -> rolRepository.save(new Rol(null, TipoRol.restaurante, "Restaurante")));

        // Crear usuarios
        Usuario consumidor = new Usuario();
        consumidor.setNombre("Consumidor Test");
        consumidor.setCorreo("consumidor@test.local");
        consumidor.setContrasena("pass");
        consumidor.setRol(rolConsumidor);
        usuarioRepository.save(consumidor);

        Usuario restaurante = new Usuario();
        restaurante.setNombre("Restaurante Test");
        restaurante.setCorreo("restaurante@test.local");
        restaurante.setContrasena("pass");
        restaurante.setRol(rolRestaurante);
        usuarioRepository.save(restaurante);

        // Crear publicación
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo("Plato Test");
        publicacion.setDescripcion("Delicioso");
        publicacion.setPrecioPlato(new BigDecimal("12000.00"));
        publicacion.setUsuario(restaurante);
        publicacionRepository.save(publicacion);

        // Crear orden via servicio
        OrdenDTO ordenRequest = new OrdenDTO();
        ordenRequest.setConsumidorId(consumidor.getId());
        ordenRequest.setRestauranteId(restaurante.getId());
        ordenRequest.setPublicacionId(publicacion.getId());
        ordenRequest.setCantidad(2);

        var orden = ordenService.crearOrden(ordenRequest);
        assertNotNull(orden.getId());

        // Actualizar estado
        ordenService.actualizarEstado(orden.getId(), com.appGastroCol.product_backend.entity.OrdenarPlato.EstadoOrden.en_proceso);

        // Obtener historial
        List<OrdenEstadoDTO> historial = ordenService.obtenerHistorial(orden.getId());
        assertNotNull(historial);
        assertTrue(historial.size() >= 2, "Debe contener al menos estado inicial y el cambio");
    }
}
