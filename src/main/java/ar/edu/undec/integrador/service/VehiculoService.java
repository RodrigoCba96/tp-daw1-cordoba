package ar.edu.undec.integrador.service;

import ar.edu.undec.integrador.dto.VehiculoRequestDTO;
import ar.edu.undec.integrador.exception.RecursoNoEncontradoException;
import ar.edu.undec.integrador.exception.ReglaDeNegocioException;
import ar.edu.undec.integrador.model.Cliente;
import ar.edu.undec.integrador.model.Vehiculo;
import ar.edu.undec.integrador.repository.ClienteRepository;
import ar.edu.undec.integrador.repository.VehiculoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final ClienteRepository clienteRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository, ClienteRepository clienteRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Vehiculo crear(VehiculoRequestDTO dto) {
        if (vehiculoRepository.existsByPatente(dto.patente())) {
            throw new ReglaDeNegocioException("La patente ya existe");
        }

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPatente(dto.patente());
        vehiculo.setMarca(dto.marca());
        vehiculo.setModelo(dto.modelo());
        vehiculo.setCliente(cliente);

        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo obtenerPorId(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Vehículo no encontrado"));
    }

    public List<Vehiculo> obtenerPorClienteId(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new RecursoNoEncontradoException("Cliente no encontrado");
        }
        return vehiculoRepository.findByCliente_Id(clienteId);
    }

    public Vehiculo actualizar(Long id, VehiculoRequestDTO dto) {
        Vehiculo vehiculo = obtenerPorId(id);
        if (!vehiculo.getPatente().equals(dto.patente()) && vehiculoRepository.existsByPatente(dto.patente())) {
            throw new ReglaDeNegocioException("La patente ya existe");
        }
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));

        vehiculo.setPatente(dto.patente());
        vehiculo.setMarca(dto.marca());
        vehiculo.setModelo(dto.modelo());
        vehiculo.setCliente(cliente);
        return vehiculoRepository.save(vehiculo);
    }

    public void eliminar(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Vehículo no encontrado");
        }
        vehiculoRepository.deleteById(id);
    }
}