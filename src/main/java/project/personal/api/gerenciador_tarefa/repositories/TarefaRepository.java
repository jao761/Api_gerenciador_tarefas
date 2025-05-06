package project.personal.api.gerenciador_tarefa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.personal.api.gerenciador_tarefa.models.Tarefa;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findAllByQuadroId(Long id);
}
