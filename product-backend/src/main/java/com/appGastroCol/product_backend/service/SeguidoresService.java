package com.appGastroCol.product_backend.service;

import java.util.List;
import com.appGastroCol.product_backend.dto.SeguidorDTO;
import com.appGastroCol.product_backend.dto.SeguidorResponseDTO;
import com.appGastroCol.product_backend.dto.UsuarioSimpleDTO;

public interface SeguidoresService {
    SeguidorResponseDTO seguirUsuario(SeguidorDTO seguidorDTO);

    void dejarDeSeguir(Long id);

    void dejarDeSeguidoPorIdUsuarios(Long seguidorId, Long seguidoId);

    List<SeguidorResponseDTO> obtenerSeguidores(Long seguidoId);

    List<SeguidorResponseDTO> obtenerSiguiendo(Long seguidorId);

    boolean verificarSeguimiento(Long seguidorId, Long seguidoId);

    long contarSeguidores(Long seguidoId);

    long contarSiguiendo(Long seguidorId);

    List<UsuarioSimpleDTO> obtenerUsuariosSeguidores(Long seguidoId);

    List<UsuarioSimpleDTO> obtenerUsuariosSiguiendo(Long seguidorId);
}
