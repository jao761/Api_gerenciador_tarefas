package project.personal.api.gerenciador_tarefa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.personal.api.gerenciador_tarefa.dtos.TarefaDTO;
import project.personal.api.gerenciador_tarefa.enuns.StatusTarefa;
import project.personal.api.gerenciador_tarefa.exceptions.CampoNaoEncontradoException;
import project.personal.api.gerenciador_tarefa.models.Quadro;
import project.personal.api.gerenciador_tarefa.models.Tarefa;
import project.personal.api.gerenciador_tarefa.repositories.QuadroRepository;
import project.personal.api.gerenciador_tarefa.repositories.TarefaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @InjectMocks
    private TarefaService service;

    @Mock
    private QuadroRepository quadroRepository;

    @Mock
    private Quadro quadro;

    @Mock
    private TarefaRepository repository;

    @Captor
    private ArgumentCaptor<Tarefa> tarefaArgumentCaptor;

    private TarefaDTO dto = new TarefaDTO("Minha tarefa", "Essa e uma tarefa", null, StatusTarefa.PENDENTE, 1L);

    private Tarefa tarefa = new Tarefa(dto, quadro);

    @Mock
    private List<Tarefa> tarefas;

    @Test
    public void testandoCriacaoTarefas() {
        given(quadroRepository.findById(dto.quadro_id())).willReturn(Optional.of(quadro));

        service.criarTarefa(dto);

        then(repository).should().save(tarefaArgumentCaptor.capture());
        var tarefaCaptor = tarefaArgumentCaptor.getValue();

        Assertions.assertEquals(tarefaCaptor.getId(), tarefa.getId());
        Assertions.assertEquals(tarefaCaptor.getTitulo(), tarefa.getTitulo());
        Assertions.assertEquals(tarefaCaptor.getDescricao(), tarefa.getDescricao());
        Assertions.assertEquals(tarefaCaptor.getDataFinalizacao(), tarefa.getDataFinalizacao());
        Assertions.assertEquals(tarefaCaptor.getQuadro(), quadro);
    }

    @Test
    public void teatandoTarefaQuadroInvalido() {
        given(quadroRepository.findById(dto.quadro_id())).willReturn(Optional.empty());

            Assertions.assertThrows(CampoNaoEncontradoException.class, () -> {
                service.criarTarefa(dto);
        });
    }

    @Test
    public void testandoRetornoDeUmQuadroTarefas() {
        // Arrange
        quadro.setId(1L); // garantir ID no mock
        tarefa.setQuadro(quadro); // garantir que a tarefa tenha esse quadro
        given(repository.findById(1L)).willReturn(Optional.of(tarefa));

        // Act
        Tarefa tarefaRetornada = service.getTarefa(1L); // <- usa retorno do service

        // Assert
        Assertions.assertEquals("Minha tarefa", tarefaRetornada.getTitulo());
        Assertions.assertEquals("Essa e uma tarefa", tarefaRetornada.getDescricao());
        Assertions.assertEquals(null, tarefaRetornada.getDataFinalizacao()); // ou o valor correto
    }

    @Test
    public void testandoTarefaNaoExistente() {
            tarefa.setQuadro(quadro);
            given(repository.findById(1L)).willReturn(Optional.empty());

            Assertions.assertThrows(CampoNaoEncontradoException.class, () -> {
                service.getTarefa(1l);
            });
    }


    @Test
    public void deveAtualizarQuandoATarefaExistir() {
        given(repository.findById(1l)).willReturn(Optional.of(tarefa));

        service.atualizarTarefa(1l, dto);

        then(repository).should().save(tarefaArgumentCaptor.capture());
        var tarefaCaptor = tarefaArgumentCaptor.getValue();

        Assertions.assertEquals(tarefaCaptor.getId(), tarefa.getId());
        Assertions.assertEquals(tarefaCaptor.getTitulo(), tarefa.getTitulo());
        Assertions.assertEquals(tarefaCaptor.getDescricao(), tarefa.getDescricao());
        Assertions.assertEquals(tarefaCaptor.getDataFinalizacao(), tarefa.getDataFinalizacao());
        Assertions.assertEquals(tarefaCaptor.getQuadro(), tarefa.getQuadro());
    }

    @Test
    public void deveFalharQuandoATarefaNaoExistir() {
        given(repository.findById(1l)).willReturn(Optional.empty());

        Assertions.assertThrows(CampoNaoEncontradoException.class, () -> {
            service.atualizarTarefa(1l, dto);
        });
    }

    @Test
    public void objetoDeveSerExcluido() {
        given(repository.findById(1l)).willReturn(Optional.of(tarefa));

        service.excluirTarefa(1l);

        then(repository).should().delete(tarefaArgumentCaptor.capture());
        var tarefaCaptor = tarefaArgumentCaptor.getValue();

        Assertions.assertEquals(tarefaCaptor, tarefa);

    }

    @Test
    public void deveOObjetoNaoDeveSerEncotrado() {
        given(repository.findById(1l)).willReturn(Optional.empty());

        Assertions.assertThrows(CampoNaoEncontradoException.class, () -> {
            service.excluirTarefa(1l);
        });
    }

    @Test
    public void deveRetornarUmMapNomeDoQuadroECampos() {

        var id = 1l;

        given(quadroRepository.getReferenceById(id)).willReturn(quadro);
        given(repository.findAllByQuadroId(id)).willReturn(tarefas);

        Assertions.assertDoesNotThrow(() -> {
            service.getQuadroById(id);
        });
    }

    @Test
    public void deveRetornarUmErroQuandoNaoENcontrarOId() {

        Long id = 1l;

        given(quadroRepository.getReferenceById(id)).willReturn(quadro);
        given(repository.findAllByQuadroId(id)).willReturn(Collections.emptyList());

        Assertions.assertThrows(CampoNaoEncontradoException.class, () -> {
            service.getQuadroById(id);
        });
    }

}