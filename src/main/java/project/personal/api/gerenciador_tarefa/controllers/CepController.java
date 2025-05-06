package project.personal.api.gerenciador_tarefa.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.api.gerenciador_tarefa.models.EnderecoResponse;
import project.personal.api.gerenciador_tarefa.service.CepService;

@RestController
@RequestMapping("api/cep")
public class CepController {

    private final CepService service;

    public CepController(CepService service) {
        this.service = service;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoResponse> getEndereco(@PathVariable String cep) {
        return ResponseEntity.ok(service.buscarEnderecoPorCep(cep));
    }
}
