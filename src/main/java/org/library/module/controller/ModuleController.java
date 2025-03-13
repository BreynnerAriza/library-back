package org.library.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.library.module.dto.ModuleResponseDto;
import org.library.module.service.IModuleQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/modules")
@RequiredArgsConstructor
@Tag(name = "Module controller", description = "Permite realizar operaciones con los modulos")
public class ModuleController {

    private final IModuleQueryService moduleQueryService;

    //Permite obtener los modulos de un usuario

    @Operation(description = "Permite obtener todos los modulos para el usuario logueado", summary = "Obtener modulos de usuario")
    @ApiResponses(value = {
            @ApiResponse(description = "Retorna todos los modulos de un usuario", responseCode = "200")
    })

    @GetMapping("/user")
    public ResponseEntity<List<ModuleResponseDto>>  getAllModules() {
        return ResponseEntity.ok(
                moduleQueryService.listModuleByUser()
        );
    }

}
