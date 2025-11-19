package com.appGastroCol.product_backend.dto;

public class UsuarioDTO {
    // Atributos del usuario
    private Long id;
    private String nombre;
    private String correo;
    private String biografia;
    private String ubicacion;
    private String imagen;
    private String rol; // "consumidor" o "restaurante"
    private String fechaCreacion;

    // Constructor vacío
    public UsuarioDTO() {
    }

    // Constructor con parámetros
    public UsuarioDTO(Long id, String nombre, String correo, String biografia,
            String ubicacion, String imagen, String rol, String fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.biografia = biografia;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}