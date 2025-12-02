package com.appGastroCol.product_backend.dto;

import java.time.LocalDateTime;
import com.appGastroCol.product_backend.entity.OrdenarPlato;

public class OrdenEstadoDTO {
    private Integer id;
    private Integer ordenId;
    private OrdenarPlato.EstadoOrden estado;
    private LocalDateTime fechaCambio;

    public OrdenEstadoDTO() {
    }

    public OrdenEstadoDTO(Integer id, Integer ordenId, OrdenarPlato.EstadoOrden estado, LocalDateTime fechaCambio) {
        this.id = id;
        this.ordenId = ordenId;
        this.estado = estado;
        this.fechaCambio = fechaCambio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
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
