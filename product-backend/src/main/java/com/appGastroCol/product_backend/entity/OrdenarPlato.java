package com.appGastroCol.product_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_ordenar_plato")
public class OrdenarPlato {
    // Atributos de la orden
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "consumidor_id", nullable = false)
    private Usuario consumidor;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Usuario restaurante;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;

    @Column(nullable = false)
    private Integer cantidad = 1;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoOrden estado = EstadoOrden.pendiente;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        if (cantidad == null) {
            cantidad = 1;
        }
        if (estado == null) {
            estado = EstadoOrden.pendiente;
        }
    }

    // Estado de la orden
    public enum EstadoOrden {
        pendiente,
        en_proceso,
        entregado,
        cancelado
    }

    // Constructor vacío
    public OrdenarPlato() {
    }

    // Constructor con parámetros
    public OrdenarPlato(Integer id, Usuario consumidor, Usuario restaurante,
            Publicacion publicacion, Integer cantidad, EstadoOrden estado,
            LocalDateTime fechaCreacion) {
        this.id = id;
        this.consumidor = consumidor;
        this.restaurante = restaurante;
        this.publicacion = publicacion;
        this.cantidad = cantidad;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Usuario consumidor) {
        this.consumidor = consumidor;
    }

    public Usuario getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Usuario restaurante) {
        this.restaurante = restaurante;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}