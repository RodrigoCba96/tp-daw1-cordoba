package ar.edu.undec.integrador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record VehiculoRequestDTO(
    @NotBlank(message = "La patente es obligatoria")
    @Pattern(regexp = "^([A-Z]{3}\\d{3}|[A-Z]{2}\\d{3}[A-Z]{2})$", message = "Formato de patente inválido")
    String patente,
    @NotBlank(message = "La marca es obligatoria")
    String marca,
    @NotBlank(message = "El modelo es obligatorio")
    String modelo,
    @NotNull(message = "El ID del cliente es obligatorio")
    Long clienteId
) {}