package ar.edu.undec.integrador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrdenRequestDTO(
    @NotNull(message = "El ID del vehículo es obligatorio")
    Long vehiculoId,
    @NotBlank(message = "La descripción es obligatoria")
    String descripcion
) {}