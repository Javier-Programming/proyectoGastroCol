package com.appGastroCol.product_backend.dto;

public class PublicacionDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private java.math.BigDecimal precioPlato;
    private String imagen;
    private Long usuarioId;
    private String fechaCreacion;

    public PublicacionDTO() {
    }

    public PublicacionDTO(Long id, String titulo, String descripcion, java.math.BigDecimal precioPlato, String imagen,
            Long usuarioId, String fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precioPlato = precioPlato;
        this.imagen = imagen;
        this.usuarioId = usuarioId;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public java.math.BigDecimal getPrecioPlato() {
        return precioPlato;
    }

    public void setPrecioPlato(java.math.BigDecimal precioPlato) {
        this.precioPlato = precioPlato;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
