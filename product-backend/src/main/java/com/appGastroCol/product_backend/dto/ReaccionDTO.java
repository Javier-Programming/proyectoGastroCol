package com.appGastroCol.product_backend.dto;

public class ReaccionDTO {
    private Long id;
    private Long usuarioId;
    private Long publicacionId;
    private java.time.LocalDateTime fechaCreacion;

    public ReaccionDTO() {
    }

    public ReaccionDTO(Long id, Long usuarioId, Long publicacionId, java.time.LocalDateTime fechaCreacion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.publicacionId = publicacionId;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public java.time.LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(java.time.LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
