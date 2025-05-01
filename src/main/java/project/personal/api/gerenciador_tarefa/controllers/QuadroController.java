package project.personal.api.gerenciador_tarefa.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.personal.api.gerenciador_tarefa.dtos.QuadroDTO;
import project.personal.api.gerenciador_tarefa.dtos.SaidaQuadroDTO;
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
    public ResponseEntity<String> criarQuadro(@RequestBody QuadroDTO dto) {
        service.salvar(dto);
        return ResponseEntity.ok("Quadro: " + dto.titulo());
    }

    @GetMapping
    public ResponseEntity<List<SaidaQuadroDTO>> mostraQuadroUsuario() {
        List<Quadro> quadros = new ArrayList<>();
        try {
            quadros = service.listarQuadros();
        } catch (NullPointerException ex) {
            return ResponseEntity.notFound().build();
        }
        var quadrosDTO = quadros.stream().map(quadro -> new SaidaQuadroDTO(quadro)).toList();
        return ResponseEntity.ok(quadrosDTO);
    }


}
