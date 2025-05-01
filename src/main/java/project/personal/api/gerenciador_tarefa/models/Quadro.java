package project.personal.api.gerenciador_tarefa.models;

import javax.annotation.processing.Generated;
import java.time.LocalDate;

public class Quadro {

    private Long id;
    private String titulo;

    public Quadro(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Quadro() {

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
