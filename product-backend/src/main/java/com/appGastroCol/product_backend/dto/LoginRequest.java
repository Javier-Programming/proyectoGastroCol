package com.appGastroCol.product_backend.dto;

public class LoginRequest {
    // Atributos
    private String correo;
    private String contrasena;

    // Constructores
    public LoginRequest() {
    }

    // Getters y Setters
    public LoginRequest(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}