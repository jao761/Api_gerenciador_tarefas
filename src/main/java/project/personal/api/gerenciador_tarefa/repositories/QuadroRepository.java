package project.personal.api.gerenciador_tarefa.repositories;

import org.springframework.stereotype.Repository;
import project.personal.api.gerenciador_tarefa.models.Quadro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class QuadroRepository {

    private List<Quadro> quadros;
    private Long proixmoId = 1l;


    public void salvar(String titulo) {
        if (this.quadros == null) {
            this.quadros = new ArrayList<>();
        }

        Quadro quadro = new Quadro(proixmoId++, titulo);
        this.quadros.add(quadro);
    }

    public List<Quadro> getQuadros() {
        return quadros;
    }

    public List<Quadro> getQuadrosByid(Long id) {
        return quadros.stream().filter(q -> q.getId() == id).toList();
    }
}
