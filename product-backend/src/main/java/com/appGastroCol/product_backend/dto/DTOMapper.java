package com.appGastroCol.product_backend.dto;

import com.appGastroCol.product_backend.entity.Comentario;
import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.entity.Publicacion;
import com.appGastroCol.product_backend.entity.OrdenarPlato;
import com.appGastroCol.product_backend.entity.OrdenEstado;
import com.appGastroCol.product_backend.dto.OrdenEstadoDTO;
import com.appGastroCol.product_backend.dto.OrdenDTO;
import com.appGastroCol.product_backend.dto.PublicacionDTO;
import com.appGastroCol.product_backend.dto.PublicacionResponseDTO;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.List;

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

    // Convertir OrdenarPlato a OrdenDTO
    public static OrdenDTO toOrdenDTO(OrdenarPlato orden) {
        if (orden == null)
            return null;
        return toOrdenDTO(orden, null);
    }

    // Convertir OrdenarPlato a OrdenDTO incluyendo historial y cálculo de total
    public static OrdenDTO toOrdenDTO(OrdenarPlato orden, List<OrdenEstadoDTO> historial) {
        if (orden == null)
            return null;

        BigDecimal total = null;
        if (orden.getPublicacion() != null && orden.getPublicacion().getPrecioPlato() != null && orden.getCantidad() != null) {
            total = orden.getPublicacion().getPrecioPlato().multiply(BigDecimal.valueOf(orden.getCantidad()));
        }

        return new OrdenDTO(
                orden.getId(),
                orden.getConsumidor() != null ? orden.getConsumidor().getId() : null,
                orden.getRestaurante() != null ? orden.getRestaurante().getId() : null,
                orden.getPublicacion() != null ? orden.getPublicacion().getId() : null,
                orden.getCantidad(),
                orden.getEstado(),
                orden.getFechaCreacion(),
                total,
                historial);
    }

    // Convertir OrdenEstado a OrdenEstadoDTO
    public static OrdenEstadoDTO toOrdenEstadoDTO(OrdenEstado ordenEstado) {
        if (ordenEstado == null)
            return null;

        return new OrdenEstadoDTO(
                ordenEstado.getId(),
                ordenEstado.getOrden() != null ? ordenEstado.getOrden().getId() : null,
                ordenEstado.getEstado(),
                ordenEstado.getFechaCambio());
    }

    // Convertir Publicacion a PublicacionResponseDTO
    public static PublicacionResponseDTO toPublicacionResponseDTO(Publicacion publicacion) {
        if (publicacion == null)
            return null;

        return new PublicacionResponseDTO(
                publicacion.getId(),
                publicacion.getTitulo(),
                publicacion.getDescripcion(),
                publicacion.getPrecioPlato(),
                publicacion.getImagen(),
                toUsuarioSimpleDTO(publicacion.getUsuario()),
                publicacion.getFechaCreacion().format(formatter));
    }

    // Convertir Publicacion a PublicacionDTO
    public static PublicacionDTO toPublicacionDTO(Publicacion publicacion) {
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