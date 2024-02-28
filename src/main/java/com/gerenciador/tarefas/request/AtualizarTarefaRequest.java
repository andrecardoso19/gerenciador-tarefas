package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.status.TarefaStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarTarefaRequest {

    private String titulo;
    private String descricao;
    private TarefaStatusEnum status;
    private Long responsavelId;
    private int quantidadeHorasEstimadas;
    private Integer quantidadeHorasRealizada;
}
