package com.appGastroCol.product_backend.dto;

// DTO para retornar reacciones con información del usuario (sin referencias circulares)
public class ReaccionResponseDTO {
    // Atributos de la reacción
    private Long id;
    private UsuarioSimpleDTO usuario;
    private Long publicacionId;
    private String fechaCreacion;

    // Constructores
    public ReaccionResponseDTO() {
    }

    // Constructor con parámetros
    public ReaccionResponseDTO(Long id, UsuarioSimpleDTO usuario, Long publicacionId, String fechaCreacion) {
        this.id = id;
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
