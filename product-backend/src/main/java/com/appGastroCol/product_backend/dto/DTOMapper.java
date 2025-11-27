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

    // Convertir Publicacion a PublicacionDTO
    public static PublicacionDTO toPublicacionDTO(com.appGastroCol.product_backend.entity.Publicacion publicacion) {
        if (publicacion == null)
            return null;

        return new PublicacionDTO(
                publicacion.getId(),
                publicacion.getTitulo(),
                publicacion.getDescripcion(),
                publicacion.getPrecioPlato(),
                publicacion.getImagen(),
                publicacion.getUsuario().getId(),
                publicacion.getFechaCreacion().format(formatter));
    }
}