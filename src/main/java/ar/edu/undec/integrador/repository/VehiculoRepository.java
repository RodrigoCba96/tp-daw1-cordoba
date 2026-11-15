package ar.edu.undec.integrador.repository;

import ar.edu.undec.integrador.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    boolean existsByPatente(String patente);
    List<Vehiculo> findByCliente_Id(Long clienteId);
}