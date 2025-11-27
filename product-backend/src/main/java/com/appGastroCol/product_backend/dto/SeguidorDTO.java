package com.appGastroCol.product_backend.dto;

public class SeguidorDTO {
    // Atributos del seguidor
    private Long id;
    private Long seguidorId;
    private Long seguidoId;
    private String fechaCreacion;

    // Constructor vacío
    public SeguidorDTO() {
    }

    // Constructor con parámetros
    public SeguidorDTO(Long id, Long seguidorId, Long seguidoId, String fechaCreacion) {
        this.id = id;
        this.seguidorId = seguidorId;
        this.seguidoId = seguidoId;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSeguidorId() {
        return seguidorId;
    }

    public void setSeguidorId(Long seguidorId) {
        this.seguidorId = seguidorId;
    }

    public Long getSeguidoId() {
        return seguidoId;
    }

    public void setSeguidoId(Long seguidoId) {
        this.seguidoId = seguidoId;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
