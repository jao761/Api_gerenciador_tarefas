package project.personal.api.gerenciador_tarefa.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.personal.api.gerenciador_tarefa.dtos.QuadroDTO;
import project.personal.api.gerenciador_tarefa.dtos.SaidaQuadroDTO;
import project.personal.api.gerenciador_tarefa.dtos.SaidaTarefaDTO;
import project.personal.api.gerenciador_tarefa.models.Quadro;
import project.personal.api.gerenciador_tarefa.service.QuadroService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("quadros")
// Esse controller cria um quadro responsavem por receber a tarefas
public class QuadroController {

    private final QuadroService service;

    public QuadroController(QuadroService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> criarQuadro(@RequestBody QuadroDTO dto) {
        service.salvar(dto);
        return ResponseEntity.ok("Quadro: " + dto.titulo());
    }


    @GetMapping
    public ResponseEntity<List<SaidaQuadroDTO>> mostraQuadroUsuario() {
        List<Quadro> quadros = new ArrayList<>();
            quadros = service.listarQuadros();
        var quadrosDTO = quadros.stream().map(quadro -> new SaidaQuadroDTO(quadro)).toList();
        return ResponseEntity.ok(quadrosDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaidaQuadroDTO> getQuadro(@PathVariable Long id, @RequestBody @Valid QuadroDTO dto) {
        var quadro = service.atualizarQuadro(id, dto);
        return ResponseEntity.ok(new SaidaQuadroDTO(quadro));
    }
}
