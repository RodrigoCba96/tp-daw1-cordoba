package ar.edu.undec.integrador.controller;

import ar.edu.undec.integrador.model.Cliente;
import ar.edu.undec.integrador.model.Vehiculo;
import ar.edu.undec.integrador.service.ClienteService;
import ar.edu.undec.integrador.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final VehiculoService vehiculoService;

    public ClienteController(ClienteService clienteService, VehiculoService vehiculoService) {
        this.clienteService = clienteService;
        this.vehiculoService = vehiculoService;
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente) {
        Cliente creado = clienteService.crear(cliente);
        return ResponseEntity.created(URI.create("/api/clientes/" + creado.getId())).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }

    @GetMapping("/{id}/vehiculos")
    public ResponseEntity<List<Vehiculo>> obtenerVehiculosPorCliente(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculoService.obtenerPorClienteId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizar(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}