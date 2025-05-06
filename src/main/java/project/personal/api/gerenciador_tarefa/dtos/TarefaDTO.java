package project.personal.api.gerenciador_tarefa.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import project.personal.api.gerenciador_tarefa.enuns.StatusTarefa;

import java.time.LocalDate;

public record TarefaDTO(

    @NotBlank(message = "Adicione titulo a tarefa")
    String titulo,
    String descricao,
    LocalDate dateFinalizacao,
    StatusTarefa status,
    @NotNull(message = "Adicione o quadro a qual pertence essa tarefa")
    Long quadro_id


) {
}
