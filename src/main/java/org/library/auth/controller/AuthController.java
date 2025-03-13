package org.library.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.auth.dto.RefreshTokenDto;
import org.library.auth.dto.LoginRequestDto;
import org.library.auth.dto.LoginSuccessDto;
import org.library.auth.service.IAuthCommandService;
import org.library.auth.service.IAuthQueryService;
import org.library.shared.dto.RequestFailed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Auth controller", description = "Representa el controlador para la autentificacion")
public class AuthController {

    private final IAuthQueryService authQueryService;
    private final IAuthCommandService authCommandService;

    @Operation(description = "Permite el login de los usuarios", summary = "Login de usuarios")
    @ApiResponses(value = {
        @ApiResponse(description = "Login exitoso", responseCode = "200"),
        @ApiResponse(description = "Hay datos incorrectos en la petición", responseCode = "400", content = @Content(schema = @Schema(implementation = RequestFailed.class))),
        @ApiResponse(description = "Credenciales incorrectas", responseCode = "401", content = @Content(schema = @Schema(implementation = RequestFailed.class))),
        @ApiResponse(description = "Cuenta desactivada", responseCode = "403", content = @Content(schema = @Schema(implementation = RequestFailed.class)))
    })

    @PostMapping("/login")
    public ResponseEntity<LoginSuccessDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.status(HttpStatus.OK.value()).body(
                authQueryService.login(loginRequestDto)
        );
    }

    @Operation(description = "Permite generar nuevos token de acceso a partir de un refresh token", summary = "Refrescar access token")
    @ApiResponses(value = {
        @ApiResponse(description = "Token de acceso refrescado", responseCode = "201"),
        @ApiResponse(description = "Hay datos incorrectos en la petición", responseCode = "400", content = @Content(schema = @Schema(implementation = RequestFailed.class))),
        @ApiResponse(description = "El refresh token proporcionado es invalido", responseCode = "401", content = @Content(schema = @Schema(implementation = RequestFailed.class))),
    })

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginSuccessDto> refreshToken(@Valid @RequestBody RefreshTokenDto refreshTokenDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(
                authCommandService.refreshToken(refreshTokenDto)
        );
    }

}
