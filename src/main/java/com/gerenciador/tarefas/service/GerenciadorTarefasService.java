package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.repository.GerenciadorTarefasRepository;
import com.gerenciador.tarefas.request.CadastrarTarefaRequest;
import com.gerenciador.tarefas.status.TarefaStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GerenciadorTarefasService {

    @Autowired
    private GerenciadorTarefasRepository gerenciadorTarefasRepository;
    @Autowired
    private UsuarioService usuarioService;

    public Tarefa salvarTarefa(CadastrarTarefaRequest request) {

        Tarefa tarefa = Tarefa.builder()
                .quantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas())
                .status(TarefaStatusEnum.CRIADA)
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .criador(usuarioService.obterUsuarioId(request.getCriadorId()).get())
                .build();

        return gerenciadorTarefasRepository.save(tarefa);
    }
}
