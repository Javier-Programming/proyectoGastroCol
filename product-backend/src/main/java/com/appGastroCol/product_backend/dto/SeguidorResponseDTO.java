package com.appGastroCol.product_backend.dto;

// DTO para retornar seguidor con información de ambos usuarios (sin referencias circulares)
public class SeguidorResponseDTO {
    // Atributos del seguidor
    private Long id;
    private UsuarioSimpleDTO seguidor;
    private UsuarioSimpleDTO seguido;
    private String fechaCreacion;

    // Constructores
    public SeguidorResponseDTO() {
    }

    // Constructor con parámetros
    public SeguidorResponseDTO(Long id, UsuarioSimpleDTO seguidor, UsuarioSimpleDTO seguido, String fechaCreacion) {
        this.id = id;
        this.seguidor = seguidor;
        this.seguido = seguido;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioSimpleDTO getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(UsuarioSimpleDTO seguidor) {
        this.seguidor = seguidor;
    }

    public UsuarioSimpleDTO getSeguido() {
        return seguido;
    }

    public void setSeguido(UsuarioSimpleDTO seguido) {
        this.seguido = seguido;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
