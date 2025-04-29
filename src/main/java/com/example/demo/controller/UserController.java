package com.example.demo.controller;

import com.example.demo.dtos.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Operaciones sobre usuarios")
public class UserController {



    @GetMapping
    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Recupera una lista de todos los usuarios registrados en el sistema.",
            tags = {"Usuarios"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = List.of(
                new UserDTO(1L, "Jesfrin Gonzalez", "jesfrin@example.com", "+502 5555 5555", LocalDate.of(1995, 5, 12), true),
                new UserDTO(2L, "Maria Lopez", "maria.lopez@example.com", "+502 4444 4444", LocalDate.of(1992, 10, 25), true),
                new UserDTO(3L, "Carlos Perez", "carlos.perez@example.com", null, null, false)
        );
        return ResponseEntity.ok(users);
    }


}