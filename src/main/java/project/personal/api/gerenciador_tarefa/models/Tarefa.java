package project.personal.api.gerenciador_tarefa.models;

import jakarta.persistence.*;
import project.personal.api.gerenciador_tarefa.dtos.TarefaDTO;
import project.personal.api.gerenciador_tarefa.enuns.StatusTarefa;

import java.time.LocalDate;

@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    @Column(name = "data_finalizacao")
    private LocalDate dataFinalizacao;
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;
    @ManyToOne
    @JoinColumn(name = "quadro_id")
    private Quadro quadro;

    public Tarefa(TarefaDTO dto, Quadro quadro) {
        this.titulo = dto.titulo();
        this.descricao = dto.descricao();
        this.dataFinalizacao = dto.dateFinalizacao();
        this.status = dto.status() == null ? StatusTarefa.PENDENTE : dto.status();
        this.quadro = quadro;
    }

    public void atualizarTarefa(TarefaDTO dto) {
        this.titulo = dto.titulo();
        this.descricao = dto.descricao() == null ? descricao : dto.descricao();
        this.dataFinalizacao = dto.dateFinalizacao() == null ? dataFinalizacao : dto.dateFinalizacao();
    }

    public Tarefa() {}

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataFinalizacao() {
        return dataFinalizacao;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public Quadro getQuadro() {
        return quadro;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }
}


