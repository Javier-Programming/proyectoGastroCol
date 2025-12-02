package com.appGastroCol.product_backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.appGastroCol.product_backend.dto.DTOMapper;
import com.appGastroCol.product_backend.dto.OrdenDTO;
import com.appGastroCol.product_backend.dto.OrdenEstadoDTO;
import com.appGastroCol.product_backend.entity.OrdenarPlato;
import com.appGastroCol.product_backend.service.OrdenService;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    // Crear una nueva orden
    @PostMapping("/crear")
    public ResponseEntity<OrdenDTO> crearOrden(@RequestBody OrdenDTO ordenDTO) {
        OrdenarPlato nuevaOrden = ordenService.crearOrden(ordenDTO);
        return new ResponseEntity<>(DTOMapper.toOrdenDTO(nuevaOrden), HttpStatus.CREATED);
    }

    // Obtener una orden por ID (opcionalmente incluir historial)
    @GetMapping("/{id}")
    public ResponseEntity<OrdenDTO> obtenerOrden(@PathVariable Integer id,
            @RequestParam(value = "withHistory", required = false, defaultValue = "false") boolean withHistory) {
        return ordenService.obtenerOrden(id)
                .map(orden -> {
                    if (withHistory) {
                        java.util.List<OrdenEstadoDTO> historial = ordenService.obtenerHistorial(id);
                        return new ResponseEntity<>(DTOMapper.toOrdenDTO(orden, historial), HttpStatus.OK);
                    }
                    return new ResponseEntity<>(DTOMapper.toOrdenDTO(orden), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Listar órdenes por consumidor
    @GetMapping("/consumidor/{consumidorId}")
    public ResponseEntity<List<OrdenDTO>> listarOrdenesPorConsumidor(@PathVariable Long consumidorId) {
        List<OrdenarPlato> ordenes = ordenService.listarOrdenesPorConsumidor(consumidorId);
        List<OrdenDTO> ordenesDTO = ordenes.stream()
                .map(DTOMapper::toOrdenDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(ordenesDTO, HttpStatus.OK);
    }

    // Listar órdenes por restaurante
    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<OrdenDTO>> listarOrdenesPorRestaurante(@PathVariable Long restauranteId) {
        List<OrdenarPlato> ordenes = ordenService.listarOrdenesPorRestaurante(restauranteId);
        List<OrdenDTO> ordenesDTO = ordenes.stream()
                .map(DTOMapper::toOrdenDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(ordenesDTO, HttpStatus.OK);
    }

    // Actualizar estado de la orden
    @PutMapping("/{id}/estado")
    public ResponseEntity<OrdenDTO> actualizarEstado(@PathVariable Integer id,
            @RequestParam OrdenarPlato.EstadoOrden nuevoEstado) {
        OrdenarPlato ordenActualizada = ordenService.actualizarEstado(id, nuevoEstado);
        return new ResponseEntity<>(DTOMapper.toOrdenDTO(ordenActualizada), HttpStatus.OK);
    }

    // Obtener historial de estados de una orden
    @GetMapping("/{id}/historial")
    public ResponseEntity<java.util.List<OrdenEstadoDTO>> obtenerHistorial(@PathVariable Integer id) {
        java.util.List<OrdenEstadoDTO> historial = ordenService.obtenerHistorial(id);
        return new ResponseEntity<>(historial, HttpStatus.OK);
    }
}
