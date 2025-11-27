package com.appGastroCol.product_backend.dto;

import java.math.BigDecimal;

public class PublicacionDTO {
    
    private Long id;
    private String titulo;
    private String descripcion;
    private BigDecimal precioPlato;
    private String imagen;
    private Long usuarioId;
    private String fechaCreacion;

    // Constructor vacío
    public PublicacionDTO() {
    }

    // Constructor con parámetros
    public PublicacionDTO(Long id, String titulo, String descripcion, BigDecimal precioPlato, String imagen, Long usuarioId, String fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precioPlato = precioPlato;
        this.imagen = imagen;
        this.usuarioId = usuarioId;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
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

    public BigDecimal getPrecioPlato() {
        return precioPlato;
    }

    public void setPrecioPlato(BigDecimal precioPlato) {
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
