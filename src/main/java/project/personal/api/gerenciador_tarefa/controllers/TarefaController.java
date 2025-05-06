package project.personal.api.gerenciador_tarefa.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.personal.api.gerenciador_tarefa.dtos.DetalhamentoTarefaDto;
import project.personal.api.gerenciador_tarefa.dtos.QuadroDTO;
import project.personal.api.gerenciador_tarefa.dtos.SaidaTarefaDTO;
import project.personal.api.gerenciador_tarefa.dtos.TarefaDTO;
import project.personal.api.gerenciador_tarefa.models.Quadro;
import project.personal.api.gerenciador_tarefa.models.Tarefa;
import project.personal.api.gerenciador_tarefa.service.TarefaService;

import java.util.List;

@RestController
@RequestMapping("tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> criarTarefa(@Valid @RequestBody TarefaDTO dto) {
        service.criarTarefa(dto);
        return ResponseEntity.ok("Tarefa criada com sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoTarefaDto> getTarefa(@PathVariable Long id) {
        Tarefa tarefa = service.getQuadro(id);
        return ResponseEntity.ok(new DetalhamentoTarefaDto(tarefa));
    }


    @GetMapping("/quadros/{id}")
    public ResponseEntity<List<SaidaTarefaDTO>> getTarefaByQuadro(@PathVariable Long id) {
        var tarefas = service.getQuadroByQuadro(id);
        return ResponseEntity.ok(tarefas.stream().map(t -> new SaidaTarefaDTO(t)).toList());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhamentoTarefaDto> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaDTO dto) {
        Tarefa tarefa = service.atualizarTarefa(id, dto);
        return ResponseEntity.ok(new DetalhamentoTarefaDto(tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity ecluirTarefa(@PathVariable Long id) {
        service.excluirTarefa(id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/aprovado/{id}")
    @Transactional
    public ResponseEntity<DetalhamentoTarefaDto> marcarComoAprovado(@PathVariable Long id) {
        var tarefa = service.marcarComoAprovado(id);
        return ResponseEntity.ok(new DetalhamentoTarefaDto(tarefa));
    }

 }
