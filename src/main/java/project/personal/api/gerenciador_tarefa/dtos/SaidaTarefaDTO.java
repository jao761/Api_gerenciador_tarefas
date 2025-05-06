package project.personal.api.gerenciador_tarefa.dtos;

import project.personal.api.gerenciador_tarefa.models.Tarefa;

public record SaidaTarefaDTO(

        String titulo,
        Long quadro_id


) {
    public SaidaTarefaDTO(Tarefa tarefa) {
        this( tarefa.getTitulo(), tarefa.getQuadro().getId());
    }
}
