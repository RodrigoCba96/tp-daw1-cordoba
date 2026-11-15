package ar.edu.undec.integrador.controller;

import ar.edu.undec.integrador.dto.EstadoUpdateDTO;
import ar.edu.undec.integrador.dto.OrdenRequestDTO;
import ar.edu.undec.integrador.dto.OrdenUpdateDTO;
import ar.edu.undec.integrador.model.EstadoOrden;
import ar.edu.undec.integrador.model.Orden;
import ar.edu.undec.integrador.service.OrdenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @PostMapping
    public ResponseEntity<Orden> crear(@Valid @RequestBody OrdenRequestDTO dto) {
        Orden creado = ordenService.crear(dto);
        return ResponseEntity.created(URI.create("/api/ordenes/" + creado.getId())).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Orden>> obtenerTodas(@RequestParam(required = false) EstadoOrden estado) {
        if (estado != null) {
            return ResponseEntity.ok(ordenService.obtenerPorEstado(estado));
        }
        return ResponseEntity.ok(ordenService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizar(@PathVariable Long id, @RequestBody OrdenUpdateDTO dto) {
        return ResponseEntity.ok(ordenService.actualizar(id, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Orden> cambiarEstado(@PathVariable Long id, @Valid @RequestBody EstadoUpdateDTO dto) {
        return ResponseEntity.ok(ordenService.cambiarEstado(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}