package com.appGastroCol.product_backend.dto;

public class ReaccionResponseDTO {

    private Long usuarioId;
    private String nombre;
    private String imagen;

    public ReaccionResponseDTO() {
    }

    public ReaccionResponseDTO(Long usuarioId, String nombre, String imagen) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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
}
