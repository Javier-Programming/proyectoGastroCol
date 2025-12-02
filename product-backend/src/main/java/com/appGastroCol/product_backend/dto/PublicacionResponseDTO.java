package com.appGastroCol.product_backend.dto;

public class PublicacionResponseDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private java.math.BigDecimal precioPlato;
    private String imagen;
    private UsuarioSimpleDTO usuario;
    private String fechaCreacion;

    public PublicacionResponseDTO() {
    }

    public PublicacionResponseDTO(Long id, String titulo, String descripcion, java.math.BigDecimal precioPlato,
            String imagen, UsuarioSimpleDTO usuario, String fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precioPlato = precioPlato;
        this.imagen = imagen;
        this.usuario = usuario;
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

    public UsuarioSimpleDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSimpleDTO usuario) {
        this.usuario = usuario;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
