package com.appGastroCol.product_backend.dto;

// DTO para retornar comentarios con información del usuario (sin referencias circulares)
public class ComentarioResponseDTO {
    // Atributos del comentario
    private Long id;
    private String contenido;
    private UsuarioSimpleDTO usuario;
    private Long publicacionId;
    private String fechaCreacion;

    // Constructores
    public ComentarioResponseDTO() {
    }

    // Constructor con parámetros
    public ComentarioResponseDTO(Long id, String contenido, UsuarioSimpleDTO usuario,
            Long publicacionId, String fechaCreacion) {
        this.id = id;
        this.contenido = contenido;
        this.usuario = usuario;
        this.publicacionId = publicacionId;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public UsuarioSimpleDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSimpleDTO usuario) {
        this.usuario = usuario;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}