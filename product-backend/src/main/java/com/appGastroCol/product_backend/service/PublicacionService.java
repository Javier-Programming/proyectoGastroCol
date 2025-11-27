package com.appGastroCol.product_backend.service;

import java.util.List;
import com.appGastroCol.product_backend.dto.PublicacionDTO;

public interface PublicacionService {

    PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    List<PublicacionDTO> obtenerTodas();

    PublicacionDTO obtenerPorId(Long id);

    List<PublicacionDTO> obtenerPorUsuario(Long usuarioId);

    void eliminarPublicacion(Long id);

    PublicacionDTO actualizarPublicacion(Long id, PublicacionDTO publicacionDTO);
}
