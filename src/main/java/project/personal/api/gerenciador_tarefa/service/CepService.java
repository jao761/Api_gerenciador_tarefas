package project.personal.api.gerenciador_tarefa.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.personal.api.gerenciador_tarefa.models.EnderecoResponse;

@Service
public class CepService {

    private final RestTemplate restTemplate;

    public CepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EnderecoResponse buscarEnderecoPorCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, EnderecoResponse.class);
    }
}
