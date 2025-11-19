package com.appGastroCol.product_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_publicaciones")
public class Publicacion {
    // Atributos de la publicación
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "precio_plato", precision = 10, scale = 2)
    private BigDecimal precioPlato;

    @Column(length = 255)
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }

    // Relaciones bidireccionales
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reaccion> reacciones;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    private List<OrdenarPlato> ordenes;

    // Constructor vacío
    public Publicacion() {
    }

    // Constructor con parámetros
    public Publicacion(Long id, String titulo, String descripcion,
            BigDecimal precioPlato, String imagen, Usuario usuario,
            LocalDateTime fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precioPlato = precioPlato;
        this.imagen = imagen;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Reaccion> getReacciones() {
        return reacciones;
    }

    public void setReacciones(List<Reaccion> reacciones) {
        this.reacciones = reacciones;
    }

    public List<OrdenarPlato> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<OrdenarPlato> ordenes) {
        this.ordenes = ordenes;
    }
}