# TP Integrador · DAW1 — Taller Mecánico

**Alumno:** Rodrigo Córdoba Rivero  
**Legajo:** 00000  
**Enunciado elegido:** taller  

## Descripción del Proyecto
API REST para la gestión de un taller mecánico, permitiendo administrar clientes, vehículos y el ciclo de vida de las órdenes de reparación.

## Endpoints

| Entidad | GET (Todos) | GET (Por ID) | POST | PUT | PATCH | DELETE |
| --- | --- | --- | --- | --- | --- | --- |
| Clientes | `/api/clientes` | `/api/clientes/{id}` | `/api/clientes` | `/api/clientes/{id}` | - | `/api/clientes/{id}` |
| Vehículos | `/api/vehiculos` | `/api/vehiculos/{id}` | `/api/vehiculos` | `/api/vehiculos/{id}` | - | `/api/vehiculos/{id}` |
| Órdenes | `/api/ordenes` | `/api/ordenes/{id}` | `/api/ordenes` | `/api/ordenes/{id}` | `/api/ordenes/{id}/estado` | `/api/ordenes/{id}` |

### Consultas Adicionales
* Obtener vehículos de un cliente: `GET /api/clientes/{id}/vehiculos`
* Filtrar órdenes por estado: `GET /api/ordenes?estado=EN_REPARACION`