package project.personal.api.gerenciador_tarefa.service;

import org.springframework.stereotype.Service;
import project.personal.api.gerenciador_tarefa.dtos.TarefaDTO;
import project.personal.api.gerenciador_tarefa.enuns.StatusTarefa;
import project.personal.api.gerenciador_tarefa.exceptions.CampoNaoEncontradoException;
import project.personal.api.gerenciador_tarefa.models.Tarefa;
import project.personal.api.gerenciador_tarefa.repositories.QuadroRepository;
import project.personal.api.gerenciador_tarefa.repositories.TarefaRepository;

import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository repository;

    private final QuadroRepository quadroRepository;

    public TarefaService(TarefaRepository repository, QuadroRepository quadroRepository) {
        this.repository = repository;
        this.quadroRepository =quadroRepository;
    }

    public void criarTarefa(TarefaDTO dto) {
        var quadro = quadroRepository.findById(dto.quadro_id())
                .orElseThrow(() -> new CampoNaoEncontradoException("Quadro não encontrado"));

        Tarefa tarefa = new Tarefa(dto, quadro);
        repository.save(tarefa);
    }

    public Tarefa getQuadro(Long id) {
        var tarefa = repository.findById(id)
                .orElseThrow(() -> new CampoNaoEncontradoException("Tarefa não encontrada"));
        return tarefa;
    }

    public Tarefa atualizarTarefa(Long id, TarefaDTO dto) {
        var tarefa = repository.findById(id)
                .orElseThrow(() -> new CampoNaoEncontradoException("Tarefa não encontrada"));
        tarefa.atualizarTarefa(dto);
        return repository.save(tarefa);
    }


    public void excluirTarefa(Long id) {
        var tarefa = repository.findById(id)
                .orElseThrow(() -> new CampoNaoEncontradoException("Tarefa não encontrada"));
        repository.delete(tarefa);
    }

    public List<Tarefa> getQuadroById(Long id) {
        var tarefas = repository.findAllByQuadroId(id);
        if (tarefas.isEmpty()) {
            throw new CampoNaoEncontradoException("Nenhuma tarefa encontrada para o quadro " + id);

        }
        return tarefas;
    }

    public Tarefa marcarComoAprovado(Long id) {
        var tarefa = repository.getReferenceById(id);
        tarefa.setStatus(StatusTarefa.CONCLUIDA);
        return tarefa;
    }
}
