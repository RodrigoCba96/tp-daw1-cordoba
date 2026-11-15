package ar.edu.undec.integrador.repository;

import ar.edu.undec.integrador.model.EstadoOrden;
import ar.edu.undec.integrador.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
    List<Orden> findByEstado(EstadoOrden estado);
    boolean existsByVehiculo_IdAndEstadoNotIn(Long vehiculoId, List<EstadoOrden> estados);
}