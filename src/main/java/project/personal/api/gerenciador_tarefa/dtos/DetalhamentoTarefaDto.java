package project.personal.api.gerenciador_tarefa.dtos;

import jakarta.persistence.Table;
import project.personal.api.gerenciador_tarefa.enuns.StatusTarefa;
import project.personal.api.gerenciador_tarefa.models.Tarefa;

import java.time.LocalDate;

public record DetalhamentoTarefaDto(

        Long id,
        String titulo,
        String descricao,
        LocalDate data_finalizacao,
        StatusTarefa status,
        Long quadro_id

) {

    public DetalhamentoTarefaDto(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataFinalizacao(),
                tarefa.getStatus(), tarefa.getQuadro().getId());
    }

}
