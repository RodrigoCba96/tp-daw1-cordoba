# TP Integrador - DAW1 - Taller Mecanico

- **Alumno:** Rodrigo Cordoba Rivero
- **Enunciado elegido:** taller

## Descripcion
API REST desarrollada con Spring Boot para la gestion de un taller mecanico. Permite administrar clientes, vehiculos y ordenes de trabajo validando el ciclo de vida de cada reparacion.

## Como ejecutar el proyecto
Ejecutar en la raiz del proyecto:
mvn spring-boot:run

## Tabla de Endpoints

| Metodo | Ruta | Descripcion | Respuestas |
| --- | --- | --- | --- |
| POST | /api/clientes | Crear un cliente | 201, 400 |
| GET | /api/clientes | Listar clientes | 200 |
| GET | /api/clientes/{id} | Obtener cliente por ID | 200, 404 |
| PUT | /api/clientes/{id} | Actualizar cliente | 200, 400, 404 |
| DELETE | /api/clientes/{id} | Eliminar cliente | 204, 404 |
| GET | /api/clientes/{id}/vehiculos | Listar vehiculos de un cliente | 200, 404 |
| POST | /api/vehiculos | Crear un vehiculo | 201, 400, 404, 409 |
| GET | /api/vehiculos | Listar todos los vehiculos | 200 |
| GET | /api/vehiculos/{id} | Obtener vehiculo por ID | 200, 404 |
| PUT | /api/vehiculos/{id} | Actualizar un vehiculo | 200, 400, 404, 409 |
| DELETE | /api/vehiculos/{id} | Eliminar un vehiculo | 204, 404 |
| POST | /api/ordenes | Registrar orden de trabajo | 201, 400, 404, 409 |
| GET | /api/ordenes | Listar ordenes o filtrar por ?estado= | 200 |
| GET | /api/ordenes/{id} | Obtener orden por ID | 200, 404 |
| PUT | /api/ordenes/{id} | Actualizar descripcion y costo final | 200, 404 |
| PATCH | /api/ordenes/{id}/estado | Cambiar estado de la orden | 200, 400, 404, 409 |
| DELETE | /api/ordenes/{id} | Eliminar una orden | 204, 404 |