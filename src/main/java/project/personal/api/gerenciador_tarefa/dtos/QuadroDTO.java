package project.personal.api.gerenciador_tarefa.dtos;

import jakarta.validation.constraints.NotBlank;
import project.personal.api.gerenciador_tarefa.models.Quadro;

public record QuadroDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        String titulo
) {
}
