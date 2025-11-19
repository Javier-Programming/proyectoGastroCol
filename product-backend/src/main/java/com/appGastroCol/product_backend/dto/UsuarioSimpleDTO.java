package com.appGastroCol.product_backend.dto;

// DTO simplificado de usuario para evitar referencias circulares
public class UsuarioSimpleDTO {
    // Atributos del usuario
    private Long id;
    private String nombre;
    private String imagen;
    private String rol;
    
    // Constructores
    public UsuarioSimpleDTO() {
    }
    
    // Constructor con parámetros
    public UsuarioSimpleDTO(Long id, String nombre, String imagen, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.rol = rol;
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
}