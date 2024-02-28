package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.request.CadastrarTarefaRequest;
import com.gerenciador.tarefas.response.CadastrarTarefaResponse;
import com.gerenciador.tarefas.service.GerenciadorTarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gerenciador-tarefas")
public class GerenciadorTarefasController {

    @Autowired
    private GerenciadorTarefasService gerenciadorTarefasService;

    @PostMapping
    public ResponseEntity<CadastrarTarefaResponse> salvarTarefa(@RequestBody CadastrarTarefaRequest request) {
        Tarefa tarefaSalva = gerenciadorTarefasService.salvarTarefa(request);

        CadastrarTarefaResponse response = CadastrarTarefaResponse
                .builder()
                .id(tarefaSalva.getId())
                .titulo(tarefaSalva.getTitulo())
                .descricao(tarefaSalva.getDescricao())
                .criador(tarefaSalva.getCriador().getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
