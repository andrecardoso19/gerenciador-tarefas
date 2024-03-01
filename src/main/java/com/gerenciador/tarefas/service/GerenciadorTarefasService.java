package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.excecoes.NaoPermitidoAlterarStatusException;
import com.gerenciador.tarefas.excecoes.NaoPermitirExcluirException;
import com.gerenciador.tarefas.excecoes.TarefaExistenteException;
import com.gerenciador.tarefas.repository.GerenciadorTarefasRepository;
import com.gerenciador.tarefas.request.AtualizarTarefaRequest;
import com.gerenciador.tarefas.request.CadastrarTarefaRequest;
import com.gerenciador.tarefas.status.TarefaStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GerenciadorTarefasService {

    @Autowired
    private GerenciadorTarefasRepository gerenciadorTarefasRepository;
    @Autowired
    private UsuarioService usuarioService;

    public Tarefa salvarTarefa(CadastrarTarefaRequest request) {

        Tarefa tarefaValidacao = gerenciadorTarefasRepository.findByTituloOrDescricao(request.getTitulo(), request.getDescricao());

        if (tarefaValidacao != null) {
            throw new TarefaExistenteException("Já existe uma tarefa criada com mesmo título ou descrição");
        }

        Tarefa tarefa = Tarefa.builder()
                .quantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas())
                .status(TarefaStatusEnum.CRIADA)
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .criador(usuarioService.obterUsuarioId(request.getCriadorId()).get())
                .build();

        return gerenciadorTarefasRepository.save(tarefa);
    }

    public Page<Tarefa> obtemTarefasPorTitulo(String titulo, Pageable pageable) {
        return this.gerenciadorTarefasRepository.findByTituloContaining(titulo, pageable);
    }

    public Page<Tarefa> obtemTodasTarefas(Pageable pageable) {
        return this.gerenciadorTarefasRepository.findAll(pageable);
    }

    public Tarefa atualizarTarefa(Long id, AtualizarTarefaRequest request) {

        Tarefa tarefa = this.gerenciadorTarefasRepository.findById(id).get();

        if (tarefa.getStatus().equals(TarefaStatusEnum.FINALIZADA)) {
            throw  new NaoPermitidoAlterarStatusException("Não permitido mover tarefa que está FINALIZADA");
        }

        if (tarefa.getStatus().equals(TarefaStatusEnum.CRIADA) && request.getStatus().equals(TarefaStatusEnum.FINALIZADA)) {
            throw  new NaoPermitidoAlterarStatusException("Não permitido mover tarefa para finalizada se estiver com status CRIADA");
        }

        if (tarefa.getStatus().equals(TarefaStatusEnum.BLOQUEADA) && request.getStatus().equals(TarefaStatusEnum.FINALIZADA)) {
            throw  new NaoPermitidoAlterarStatusException("Não permitido mover tarefa para finalizada se estiver com status BLOQUEADA");
        }

        tarefa.setQuantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas());
        tarefa.setStatus(request.getStatus());
        tarefa.setTitulo(request.getTitulo());
        tarefa.setDescricao(request.getDescricao());
        tarefa.setResponsavel(usuarioService.obterUsuarioId(request.getResponsavelId()).get());
        tarefa.setQuantidadeHorasRealizada(request.getQuantidadeHorasRealizada());

        this.gerenciadorTarefasRepository.save(tarefa);

        return tarefa;
    }

    public void excluirTarefa(Long id) {

        Tarefa tarefa = this.gerenciadorTarefasRepository.findById(id).get();

        if (!TarefaStatusEnum.CRIADA.equals(tarefa.getStatus())) {
            throw new NaoPermitirExcluirException();
        }

        this.gerenciadorTarefasRepository.deleteById(id);
    }
}
