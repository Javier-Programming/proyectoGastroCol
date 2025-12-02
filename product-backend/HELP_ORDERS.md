Contrato del módulo de órdenes
===============================

Resumen
-------
Este documento define el contrato (endpoints, requests/responses, validaciones y ejemplos) para el
módulo `orders` del backend.

Base path: /api/ordenes

Entidades relevantes (resumen)
- Orden: representa una orden hecha por un consumidor sobre una publicación (plato). En el código esta
  entidad está implementada como `OrdenarPlato`.
- OrdenEstado: registro histórico de cambios de estado de una orden.

Endpoints
---------

1) Crear orden
- Método: POST
- URL: /api/ordenes/crear
- Request body: OrdenDTO (json) mínimo requerido:
  {
    "consumidorId": 123,
    "restauranteId": 45,
    "publicacionId": 987,
    "cantidad": 2
  }
- Validaciones:
  - `consumidorId`, `restauranteId`, `publicacionId` deben existir en la BD.
  - `cantidad` >= 1 (si no viene, por defecto 1).
- Response: 201 Created con `OrdenDTO` completo (incluye id, estado inicial, fechaCreacion).

2) Obtener orden por id
- Método: GET
- URL: /api/ordenes/{id}
- Response: 200 OK con `OrdenDTO` o 404 si no existe.

3) Listar órdenes por consumidor
- Método: GET
- URL: /api/ordenes/consumidor/{consumidorId}
- Response: 200 OK con lista de `OrdenDTO` (light: historial puede venir nulo) o [] si no hay órdenes.

4) Listar órdenes por restaurante
- Método: GET
- URL: /api/ordenes/restaurante/{restauranteId}
- Response: 200 OK con lista de `OrdenDTO`.

5) Actualizar estado de la orden
- Método: PUT
- URL: /api/ordenes/{id}/estado?nuevoEstado=pendiente|en_proceso|entregado|cancelado
- Validaciones:
  - El `nuevoEstado` debe ser una de las opciones enumeradas.
  - La orden debe existir.
- Response: 200 OK con `OrdenDTO` actualizado.
  - Se guarda un registro en `OrdenEstado` con la marca de tiempo del cambio.

6) Obtener historial de una orden
- Método: GET
- URL: /api/ordenes/{id}/historial
- Response: 200 OK con lista de `OrdenEstadoDTO` ordenada por fecha ascendente.

Formato de DTOs (JSON)
- OrdenDTO (respuesta):
  {
    "id": 1,
    "consumidorId": 123,
    "restauranteId": 45,
    "publicacionId": 987,
    "cantidad": 2,
    "estado": "pendiente",
    "fechaCreacion": "2025-12-01T19:01:00",
    "total": 35000.00,
    "historial": [ { "id": 1, "ordenId": 1, "estado": "pendiente", "fechaCambio": "2025-12-01T19:01:00" }, ... ]
  }

- OrdenEstadoDTO:
  {
    "id": 1,
    "ordenId": 1,
    "estado": "pendiente",
    "fechaCambio": "2025-12-01T19:01:00"
  }

Errores y códigos
- 400 Bad Request: datos inválidos (cantidad <= 0, estado inválido, payload incompleto).
- 404 Not Found: recursos referenciados no encontrados (consumidor, restaurante, publicacion, orden).
- 500 Internal Server Error: error inesperado del servidor.

Notas de implementación
- En la implementación actual la entidad principal es `OrdenarPlato` y contiene `publicacion` (plato)
  y `cantidad`. El `total` puede calcularse como `publicacion.precioPlato * cantidad` en el mapper o
  en el servicio.
- Para pruebas locales y CI recomiendo usar un profile de test con H2 in-memory para evitar que
  Hibernate intente modificar esquemas en la BD real durante `mvn test`.

Siguientes pasos recomendados
- Implementar mapeo del `total` en `DTOMapper.toOrdenDTO` (leer `publicacion.getPrecioPlato()` si existe).
- Añadir tests: flujo crear orden -> actualizar estado -> obtener historial (usar H2).
- Añadir validaciones más estrictas (por ejemplo, reglas de negocio al permitir cambios de estado).
