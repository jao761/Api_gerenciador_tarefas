package project.personal.api.gerenciador_tarefa.models;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import project.personal.api.gerenciador_tarefa.dtos.QuadroDTO;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quadros")
public class Quadro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @OneToMany(mappedBy = "quadro", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<>();

    public Quadro(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public void atualizar(QuadroDTO dto) {
        this.titulo = dto.titulo();
    }

    public Quadro() {

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Quadro(QuadroDTO dto){
        this.titulo = dto.titulo();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
