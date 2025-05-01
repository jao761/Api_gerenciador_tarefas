package project.personal.api.gerenciador_tarefa.dtos;

import jakarta.validation.constraints.NotBlank;
import project.personal.api.gerenciador_tarefa.models.Quadro;

public record SaidaQuadroDTO(

                Long id,
                String titulo
) {
    public SaidaQuadroDTO(Quadro quadro) {
        this(quadro.getId(), quadro.getTitulo());
    }
}
