package project.personal.api.gerenciador_tarefa.service;

import org.springframework.expression.spel.ast.QualifiedIdentifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import project.personal.api.gerenciador_tarefa.dtos.QuadroDTO;
import project.personal.api.gerenciador_tarefa.models.Quadro;
import project.personal.api.gerenciador_tarefa.repositories.QuadroRepository;

import java.util.List;

@Service
public class QuadroService {

    private final QuadroRepository repository;

    public QuadroService(QuadroRepository repository) {
        this.repository = repository;
    }

    public void salvar(QuadroDTO dto) {
        repository.salvar(dto.titulo());
    }

    public List<Quadro> listarQuadros() {
        if (repository.getQuadros() == null) {
            throw new NullPointerException("A lista noa deve estar vazia");
        }
        return repository.getQuadros();
    }


}
