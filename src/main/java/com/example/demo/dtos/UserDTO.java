package com.example.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa un usuario en el sistema")
public class UserDTO {


    @Schema(
            description = "Identificador único del usuario.",
            example = "123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long id;

    @Schema(
            description = "Nombre completo del usuario.",
            example = "Jesfrin Gonzalez",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @Schema(
            description = "Correo electrónico de contacto.",
            example = "jesfrin@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @Schema(
            description = "Número de teléfono del usuario (opcional).",
            example = "+502 5555 5555",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String phoneNumber;

    @Schema(
            description = "Fecha de nacimiento del usuario (opcional).",
            example = "1995-05-12",
            type = "string",
            format = "date",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private LocalDate birthDate;

    @Schema(
            description = "Indica si el usuario está activo en el sistema.",
            example = "true",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Boolean active;
}