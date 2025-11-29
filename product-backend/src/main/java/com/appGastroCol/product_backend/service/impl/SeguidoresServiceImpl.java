package com.appGastroCol.product_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.appGastroCol.product_backend.dto.SeguidorDTO;
import com.appGastroCol.product_backend.dto.SeguidorResponseDTO;
import com.appGastroCol.product_backend.dto.UsuarioSimpleDTO;
import com.appGastroCol.product_backend.dto.DTOMapper;
import com.appGastroCol.product_backend.entity.Seguidor;
import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.repository.SeguidoresRepository;
import com.appGastroCol.product_backend.repository.UsuarioRepository;
import com.appGastroCol.product_backend.service.SeguidoresService;

@Service
public class SeguidoresServiceImpl implements SeguidoresService {

    private final SeguidoresRepository seguidoresRepository;
    private final UsuarioRepository usuarioRepository;

    public SeguidoresServiceImpl(SeguidoresRepository seguidoresRepository,
            UsuarioRepository usuarioRepository) {
        this.seguidoresRepository = seguidoresRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public SeguidorResponseDTO seguirUsuario(SeguidorDTO seguidorDTO) {

        if (seguidorDTO == null) {
            throw new IllegalArgumentException("El seguidor no puede ser nulo");
        }

        Long seguidorId = seguidorDTO.getSeguidorId();
        if (seguidorId == null) {
            throw new IllegalArgumentException("El ID del seguidor no puede ser nulo");
        }

        Long seguidoId = seguidorDTO.getSeguidoId();
        if (seguidoId == null) {
            throw new IllegalArgumentException("El ID del seguido no puede ser nulo");
        }

        // Evitar que un usuario se siga a sí mismo
        if (seguidorId.equals(seguidoId)) {
            throw new RuntimeException("Un usuario no puede seguirse a sí mismo");
        }

        // Buscar usuario seguidor
        Usuario seguidor = usuarioRepository.findById(seguidorId)
                .orElseThrow(() -> new RuntimeException("Usuario seguidor no encontrado con ID: " + seguidorId));

        // Buscar usuario a seguir
        Usuario seguido = usuarioRepository.findById(seguidoId)
                .orElseThrow(() -> new RuntimeException("Usuario a seguir no encontrado con ID: " + seguidoId));

        // Verificar si ya existe la relación de seguimiento
        if (seguidoresRepository.existsBySeguidorIdAndSeguidoId(seguidorId, seguidoId)) {
            throw new RuntimeException("El usuario ya está siendo seguido por este usuario");
        }

        // Crear seguidor
        Seguidor nuevoSeguidor = new Seguidor();
        nuevoSeguidor.setSeguidor(seguidor);
        nuevoSeguidor.setSeguido(seguido);

        Seguidor seguidorGuardado = seguidoresRepository.save(nuevoSeguidor);
        return DTOMapper.toSeguidorResponseDTO(seguidorGuardado);
    }

    @Override
    public void dejarDeSeguir(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        if (!seguidoresRepository.existsById(id)) {
            throw new RuntimeException("Seguidor no encontrado con ID: " + id);
        }

        seguidoresRepository.deleteById(id);
    }

    @Override
    public void dejarDeSeguidoPorIdUsuarios(Long seguidorId, Long seguidoId) {
        if (seguidorId == null) {
            throw new IllegalArgumentException("El ID del seguidor no puede ser nulo");
        }

        if (seguidoId == null) {
            throw new IllegalArgumentException("El ID del seguido no puede ser nulo");
        }

        if (!seguidoresRepository.existsBySeguidorIdAndSeguidoId(seguidorId, seguidoId)) {
            throw new RuntimeException("No existe relación de seguimiento entre estos usuarios");
        }

        seguidoresRepository.deleteBySeguidorIdAndSeguidoId(seguidorId, seguidoId);
    }

    @Override
    public List<SeguidorResponseDTO> obtenerSeguidores(Long seguidoId) {
        if (seguidoId == null) {
            throw new IllegalArgumentException("El ID del seguido no puede ser nulo");
        }

        List<Seguidor> seguidores = seguidoresRepository.findBySeguidoId(seguidoId);

        return seguidores.stream()
                .map(DTOMapper::toSeguidorResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SeguidorResponseDTO> obtenerSiguiendo(Long seguidorId) {
        if (seguidorId == null) {
            throw new IllegalArgumentException("El ID del seguidor no puede ser nulo");
        }

        List<Seguidor> siguiendo = seguidoresRepository.findBySeguidorId(seguidorId);

        return siguiendo.stream()
                .map(DTOMapper::toSeguidorResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean verificarSeguimiento(Long seguidorId, Long seguidoId) {
        if (seguidorId == null || seguidoId == null) {
            throw new IllegalArgumentException("Los IDs de seguidor y seguido no pueden ser nulos");
        }

        return seguidoresRepository.existsBySeguidorIdAndSeguidoId(seguidorId, seguidoId);
    }

    @Override
    public long contarSeguidores(Long seguidoId) {
        if (seguidoId == null) {
            throw new IllegalArgumentException("El ID del seguido no puede ser nulo");
        }

        return seguidoresRepository.countBySeguidoId(seguidoId);
    }

    @Override
    public long contarSiguiendo(Long seguidorId) {
        if (seguidorId == null) {
            throw new IllegalArgumentException("El ID del seguidor no puede ser nulo");
        }

        return seguidoresRepository.countBySeguidorId(seguidorId);
    }

    @Override
    public List<UsuarioSimpleDTO> obtenerUsuariosSeguidores(Long seguidoId) {
        if (seguidoId == null) {
            throw new IllegalArgumentException("El ID del seguido no puede ser nulo");
        }

        List<Seguidor> seguidores = seguidoresRepository.findBySeguidoId(seguidoId);

        return seguidores.stream()
                .map(s -> DTOMapper.toUsuarioSimpleDTO(s.getSeguidor()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioSimpleDTO> obtenerUsuariosSiguiendo(Long seguidorId) {
        if (seguidorId == null) {
            throw new IllegalArgumentException("El ID del seguidor no puede ser nulo");
        }

        List<Seguidor> siguiendo = seguidoresRepository.findBySeguidorId(seguidorId);

        return siguiendo.stream()
                .map(s -> DTOMapper.toUsuarioSimpleDTO(s.getSeguido()))
                .collect(Collectors.toList());
    }
}
