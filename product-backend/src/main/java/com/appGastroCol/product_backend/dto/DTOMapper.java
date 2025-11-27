package com.appGastroCol.product_backend.dto;

import com.appGastroCol.product_backend.entity.Comentario;
import com.appGastroCol.product_backend.entity.Usuario;
import java.time.format.DateTimeFormatter;

// Clase helper para convertir entidades a DTOs
public class DTOMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Convertir Usuario a UsuarioSimpleDTO
    public static UsuarioSimpleDTO toUsuarioSimpleDTO(Usuario usuario) {
        if (usuario == null)
            return null;

        return new UsuarioSimpleDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getImagen(),
                usuario.getRol().getRol().name());
    }

    // Convertir Usuario a UsuarioDTO
    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null)
            return null;

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getBiografia(),
                usuario.getUbicacion(),
                usuario.getImagen(),
                usuario.getRol().getRol().name(),
                usuario.getFechaCreacion().format(formatter));
    }

    // Convertir Comentario a ComentarioResponseDTO
    public static ComentarioResponseDTO toComentarioResponseDTO(Comentario comentario) {
        if (comentario == null)
            return null;

        return new ComentarioResponseDTO(
                comentario.getId(),
                comentario.getContenido(),
                toUsuarioSimpleDTO(comentario.getUsuario()),
                comentario.getPublicacion().getId(),
                comentario.getFechaCreacion().format(formatter));
    }

    // Convertir Comentario a ComentarioDTO
    public static ComentarioDTO toComentarioDTO(Comentario comentario) {
        if (comentario == null)
            return null;

        return new ComentarioDTO(
                comentario.getId(),
                comentario.getContenido(),
                comentario.getUsuario().getId(),
                comentario.getPublicacion().getId(),
                comentario.getFechaCreacion().format(formatter));
    }

    // Convertir Reaccion a ReaccionResponseDTO
    public static ReaccionResponseDTO toReaccionResponseDTO(
            com.appGastroCol.product_backend.entity.Reaccion reaccion) {
        if (reaccion == null)
            return null;

        return new ReaccionResponseDTO(
                reaccion.getId(),
                toUsuarioSimpleDTO(reaccion.getUsuario()),
                reaccion.getPublicacion().getId(),
                reaccion.getFechaCreacion().format(formatter));
    }

    // Convertir Reaccion a ReaccionDTO
    public static ReaccionDTO toReaccionDTO(com.appGastroCol.product_backend.entity.Reaccion reaccion) {
        if (reaccion == null)
            return null;

        return new ReaccionDTO(
                reaccion.getId(),
                reaccion.getUsuario().getId(),
                reaccion.getPublicacion().getId(),
                reaccion.getFechaCreacion().format(formatter));
    }
}