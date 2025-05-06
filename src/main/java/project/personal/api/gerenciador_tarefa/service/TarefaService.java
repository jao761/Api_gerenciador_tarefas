package project.personal.api.gerenciador_tarefa.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import project.personal.api.gerenciador_tarefa.dtos.DetalhamentoTarefaDto;
import project.personal.api.gerenciador_tarefa.dtos.TarefaDTO;
import project.personal.api.gerenciador_tarefa.enuns.StatusTarefa;
import project.personal.api.gerenciador_tarefa.models.Quadro;
import project.personal.api.gerenciador_tarefa.models.Tarefa;
import project.personal.api.gerenciador_tarefa.repositories.QuadroRepository;
import project.personal.api.gerenciador_tarefa.repositories.TarefaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
                .orElseThrow(() -> new EntityNotFoundException("Quadro não encontrado"));

        Tarefa tarefa = new Tarefa(dto, quadro);
        repository.save(tarefa);
    }

    public Tarefa getQuadro(Long id) {
        var tarefa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
        return tarefa;
    }

    public Tarefa atualizarTarefa(Long id, TarefaDTO dto) {
        var tarefa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
        tarefa.atualizarTarefa(dto);
        return repository.save(tarefa);
    }


    public void excluirTarefa(Long id) {
        var tarefa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
        repository.delete(tarefa);
    }

    public List<Tarefa> getQuadroByQuadro(Long id) {
        var tarefas = repository.findAllByQuadroId(id);
        if (tarefas.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma tarefa encontrada para o quadro " + id);

        }
        return tarefas;
    }

    public Tarefa marcarComoAprovado(Long id) {
        var tarefa = repository.getReferenceById(id);
        tarefa.setStatus(StatusTarefa.CONCLUIDA);
        return tarefa;
    }
}
