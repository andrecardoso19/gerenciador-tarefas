package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.status.TarefaStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CadastrarTarefaRequest {

    private String titulo;
    private String descricao;
    private Long criadorId;
    private int quantidadeHorasEstimadas;
}
