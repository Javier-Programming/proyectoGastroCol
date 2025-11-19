package com.appGastroCol.product_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_roles")
public class Rol {
    // Atributos del rol
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRol rol;

    @Column(length = 255)
    private String descripcion;

    public enum TipoRol {
        consumidor,
        restaurante
    }

    // Constructor vacío
    public Rol() {
    }

    // Constructor con parámetros
    public Rol(Long id, TipoRol rol, String descripcion) {
        this.id = id;
        this.rol = rol;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoRol getRol() {
        return rol;
    }

    public void setRol(TipoRol rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}