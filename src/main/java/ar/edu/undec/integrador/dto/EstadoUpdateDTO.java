package ar.edu.undec.integrador.dto;

import ar.edu.undec.integrador.model.EstadoOrden;
import jakarta.validation.constraints.NotNull;

public record EstadoUpdateDTO(
    @NotNull(message = "El estado es obligatorio")
    EstadoOrden estado
) {}