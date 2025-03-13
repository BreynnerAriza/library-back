package org.library.module.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "Representa un modulo del sistema")
public record   ModuleResponseDto(
        @Schema(description = "Representa el id del modulo", example = "Libros")
        String name,
        @Schema(description = "Representa la clase icono del modulo", example = "bx bxs-book")
        String icon,
        @Schema(description = "Presenta la ruta del modulo", example = "/libros")
        String route
) implements Serializable { }
