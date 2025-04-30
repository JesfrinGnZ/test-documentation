package com.example.demo.controller;

import com.example.demo.dtos.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Operation(
            summary = "Crear usuario con ejemplo explícito",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Usuario válido básico",
                                            value = """
                        {
                          "id": 777,
                          "name": "Ana Pérez",
                          "email": "ana.perez@example.com",
                          "phoneNumber": "+502 1111 2222",
                          "birthDate": "1990-10-10",
                          "active": true
                        }
                        """
                                    )
                            }
                    )
            )
    )
    @PostMapping("/custom-example")
    public ResponseEntity<UserDTO> createUserWithExample(@RequestBody UserDTO user) {
        return ResponseEntity.ok(user);
    }


    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(user);
    }


    @Operation(
            summary = "Actualizar un usuario",
            description = "Actualiza la información de un usuario existente por su ID.",
            tags = {"Usuarios"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Nuevos datos del usuario.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class),
                            examples = @ExampleObject(
                                    name = "Usuario actualizado",
                                    value = """
                        {
                          "id": 1,
                          "name": "Jesfrin Gonzalez Actualizado",
                          "email": "jesfrin.gonzalez@example.com",
                          "phoneNumber": "+502 7777 7777",
                          "birthDate": "1995-05-12",
                          "active": false
                        }
                        """
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @Parameter(description = "ID del usuario a actualizar", example = "1")
            @PathVariable Long id,
            @RequestBody UserDTO user) {
        user.setId(id);
        return ResponseEntity.ok(user);
    }


    @Operation(
            summary = "Eliminar un usuario",
            description = "Elimina un usuario existente por su ID.",
            tags = {"Usuarios"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID del usuario a eliminar", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.noContent().build(); // Simulación
    }


}