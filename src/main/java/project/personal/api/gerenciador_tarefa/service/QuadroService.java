package project.personal.api.gerenciador_tarefa.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import project.personal.api.gerenciador_tarefa.dtos.QuadroDTO;
import project.personal.api.gerenciador_tarefa.models.Quadro;
import project.personal.api.gerenciador_tarefa.models.Tarefa;
import project.personal.api.gerenciador_tarefa.repositories.QuadroRepository;

import java.util.List;

@Service
public class QuadroService {

    private final QuadroRepository repository;

    public QuadroService(QuadroRepository repository) {
        this.repository = repository;
    }

    public void salvar(QuadroDTO dto) {
        repository.save(new Quadro(dto));
    }

    public List<Quadro> listarQuadros() {
        List<Quadro> listaQudros = repository.findAll();
        if (listaQudros == null) {
            throw new NullPointerException("A lista nao deve estar vazia");
        }
        return listaQudros;
    }
}
