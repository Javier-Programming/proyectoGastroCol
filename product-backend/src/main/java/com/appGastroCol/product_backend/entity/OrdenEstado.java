package com.appGastroCol.product_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_orden_estado")
public class OrdenEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private OrdenarPlato orden;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrdenarPlato.EstadoOrden estado;

    @Column(name = "fecha_cambio", nullable = false)
    private LocalDateTime fechaCambio;

    public OrdenEstado() {
    }

    public OrdenEstado(Integer id, OrdenarPlato orden, OrdenarPlato.EstadoOrden estado, LocalDateTime fechaCambio) {
        this.id = id;
        this.orden = orden;
        this.estado = estado;
        this.fechaCambio = fechaCambio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrdenarPlato getOrden() {
        return orden;
    }

    public void setOrden(OrdenarPlato orden) {
        this.orden = orden;
    }

    public OrdenarPlato.EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(OrdenarPlato.EstadoOrden estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDateTime fechaCambio) {
        this.fechaCambio = fechaCambio;
    }
}
