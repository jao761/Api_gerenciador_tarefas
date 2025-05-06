package project.personal.api.gerenciador_tarefa.models;

public record EnderecoResponse(

        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf

) {
}
