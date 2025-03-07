package org.library.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Schema(description = "Representa la solicitud para un login")
public record LoginRequest(

        @Schema(description = "Representa el username del usuario", example = "usuario")
        @NotBlank(message = "El usuario es obligatorio")
        String username,
        @Schema(description = "Representa la contraseña del usuario", example = "changeme")
        @NotBlank(message = "La contraseña es obligatoria")
        String password

) implements Serializable { }
