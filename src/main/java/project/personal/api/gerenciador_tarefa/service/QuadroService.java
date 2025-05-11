package project.personal.api.gerenciador_tarefa.service;

import org.springframework.stereotype.Service;
import project.personal.api.gerenciador_tarefa.dtos.QuadroDTO;
import project.personal.api.gerenciador_tarefa.exceptions.CampoNaoEncontradoException;
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
        repository.save(new Quadro(dto));
    }

    public List<Quadro> listarQuadros() {
        List<Quadro> listaQudros = repository.findAll();
        if (listaQudros == null) {
            throw new CampoNaoEncontradoException("A lista nao deve estar vazia");
        }
        return listaQudros;
    }

    public Quadro atualizarQuadro(Long id, QuadroDTO dto) {
        var quadro = repository.findById(id)
                .orElseThrow(() -> new CampoNaoEncontradoException("Id nao encontrado"));
        quadro.atualizar(dto);
        return repository.save(quadro);
    }
}
