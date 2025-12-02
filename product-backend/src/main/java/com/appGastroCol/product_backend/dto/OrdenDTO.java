package com.appGastroCol.product_backend.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;
import com.appGastroCol.product_backend.entity.OrdenarPlato.EstadoOrden;

public class OrdenDTO {
    private Integer id;
    private Long consumidorId;
    private Long restauranteId;
    private Long publicacionId;
    private Integer cantidad;
    private EstadoOrden estado;
    private LocalDateTime fechaCreacion;
    // Precio total estimado de la orden (puede calcularse como publicacion.precio * cantidad)
    private BigDecimal total;
    // Historial de estados (opcional en respuestas ligeras)
    private List<OrdenEstadoDTO> historial;

    public OrdenDTO() {
    }

    public OrdenDTO(Integer id, Long consumidorId, Long restauranteId, Long publicacionId, Integer cantidad,
            EstadoOrden estado, LocalDateTime fechaCreacion) {
        this.id = id;
        this.consumidorId = consumidorId;
        this.restauranteId = restauranteId;
        this.publicacionId = publicacionId;
        this.cantidad = cantidad;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    // Nuevo constructor completo
    public OrdenDTO(Integer id, Long consumidorId, Long restauranteId, Long publicacionId, Integer cantidad,
            EstadoOrden estado, LocalDateTime fechaCreacion, BigDecimal total, List<OrdenEstadoDTO> historial) {
        this.id = id;
        this.consumidorId = consumidorId;
        this.restauranteId = restauranteId;
        this.publicacionId = publicacionId;
        this.cantidad = cantidad;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.total = total;
        this.historial = historial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getConsumidorId() {
        return consumidorId;
    }

    public void setConsumidorId(Long consumidorId) {
        this.consumidorId = consumidorId;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrdenEstadoDTO> getHistorial() {
        return historial;
    }

    public void setHistorial(List<OrdenEstadoDTO> historial) {
        this.historial = historial;
    }
}
