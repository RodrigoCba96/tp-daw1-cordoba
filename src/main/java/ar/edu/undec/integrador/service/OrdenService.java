package ar.edu.undec.integrador.service;

import ar.edu.undec.integrador.dto.EstadoUpdateDTO;
import ar.edu.undec.integrador.dto.OrdenRequestDTO;
import ar.edu.undec.integrador.dto.OrdenUpdateDTO;
import ar.edu.undec.integrador.exception.RecursoNoEncontradoException;
import ar.edu.undec.integrador.exception.ReglaDeNegocioException;
import ar.edu.undec.integrador.model.EstadoOrden;
import ar.edu.undec.integrador.model.Orden;
import ar.edu.undec.integrador.model.Vehiculo;
import ar.edu.undec.integrador.repository.OrdenRepository;
import ar.edu.undec.integrador.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService {

    private final OrdenRepository ordenRepository;
    private final VehiculoRepository vehiculoRepository;

    public OrdenService(OrdenRepository ordenRepository, VehiculoRepository vehiculoRepository) {
        this.ordenRepository = ordenRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public Orden crear(OrdenRequestDTO dto) {
        Vehiculo vehiculo = vehiculoRepository.findById(dto.vehiculoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Vehículo no encontrado"));

        List<EstadoOrden> estadosCerrados = List.of(EstadoOrden.ENTREGADO, EstadoOrden.CANCELADO);
        if (ordenRepository.existsByVehiculo_IdAndEstadoNotIn(vehiculo.getId(), estadosCerrados)) {
            throw new ReglaDeNegocioException("El vehículo ya tiene una orden abierta");
        }

        Orden orden = new Orden();
        orden.setVehiculo(vehiculo);
        orden.setDescripcion(dto.descripcion());
        orden.setEstado(EstadoOrden.INGRESADO);

        return ordenRepository.save(orden);
    }

    public List<Orden> obtenerTodas() {
        return ordenRepository.findAll();
    }

    public Orden obtenerPorId(Long id) {
        return ordenRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden no encontrada"));
    }

    public List<Orden> obtenerPorEstado(EstadoOrden estado) {
        return ordenRepository.findByEstado(estado);
    }

    public Orden actualizar(Long id, OrdenUpdateDTO dto) {
        Orden orden = obtenerPorId(id);

        if (dto.descripcion() != null && !dto.descripcion().isBlank()) {
            orden.setDescripcion(dto.descripcion());
        }
        if (dto.costoFinal() != null) {
            orden.setCostoFinal(dto.costoFinal());
        }

        return ordenRepository.save(orden);
    }

    public Orden cambiarEstado(Long id, EstadoUpdateDTO dto) {
        Orden orden = obtenerPorId(id);
        EstadoOrden actual = orden.getEstado();
        EstadoOrden nuevo = dto.estado();

        boolean transicionValida = switch (actual) {
            case INGRESADO -> nuevo == EstadoOrden.EN_REPARACION || nuevo == EstadoOrden.CANCELADO;
            case EN_REPARACION -> nuevo == EstadoOrden.TERMINADO;
            case TERMINADO -> nuevo == EstadoOrden.ENTREGADO;
            case ENTREGADO, CANCELADO -> false;
        };

        if (!transicionValida) {
            throw new ReglaDeNegocioException("Transición de estado no permitida");
        }

        if (nuevo == EstadoOrden.ENTREGADO && orden.getCostoFinal() == null) {
            throw new ReglaDeNegocioException("No se puede entregar sin cargar el costo final");
        }

        orden.setEstado(nuevo);
        return ordenRepository.save(orden);
    }

    public void eliminar(Long id) {
        if (!ordenRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Orden no encontrada");
        }
        ordenRepository.deleteById(id);
    }
}