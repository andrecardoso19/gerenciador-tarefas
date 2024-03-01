package com.gerenciador.tarefas.excecoes;

import com.gerenciador.tarefas.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExcecoesHandler {

    @ExceptionHandler(NaoPermitirExcluirException.class)
    public ResponseEntity<ErrorResponse> naoPermitirExcluirExceptionHandler(NaoPermitirExcluirException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("codigo", ErrosEnum.NAO_PERMITIDO_EXCLUIR.toString());
        response.put("Mensagem", "Não permitido excluir tarefa com o status diferente de CRIADA");

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .erros(Collections.singletonList(response))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NaoPermitidoAlterarStatusException.class)
    public ResponseEntity<ErrorResponse> naoPermitidoAlterarStatusExceptionHandler(NaoPermitidoAlterarStatusException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("codigo", ErrosEnum.NAO_PERMITIDO_MUDAR_STATUS.toString());
        response.put("Mensagem", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .erros(Collections.singletonList(response))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TarefaExistenteException.class)
    public ResponseEntity<ErrorResponse> tarefaExistenteExceptionHandler(TarefaExistenteException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("codigo", ErrosEnum.TAREFA_EXISTENTE.toString());
        response.put("Mensagem", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .erros(Collections.singletonList(response))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
