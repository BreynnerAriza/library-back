package org.library.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "Representa la solicitud de login exitosa")
public record LoginSuccessDto(
    @Schema(description = "Representa el token de acceso", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJjYjYyZTk0OC0xYTM2LTQyNmQtYjRlNy02YmVkYzNkMjRmMTIiLCJzdWIiOiJNb2VydVRzdWt5IiwiaXNzIjoiQVVUSC0wIiwiaWF0IjoxNzQxMjkyMzIyLCJleHAiOjE3NDEyOTM1MjJ9.MGu549tXbOia7lBBdTGm6c4uShiHn7BoU6sboBw_52E")
    String accessToken,
    @Schema(description = "Representa el token de refresco que permite generar nuevos token de acceso")
    String refreshToken
) implements Serializable { }
