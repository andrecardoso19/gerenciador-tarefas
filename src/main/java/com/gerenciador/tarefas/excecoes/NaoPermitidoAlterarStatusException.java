package com.gerenciador.tarefas.excecoes;

public class NaoPermitidoAlterarStatusException extends RuntimeException{

    public NaoPermitidoAlterarStatusException() {

    }

    public NaoPermitidoAlterarStatusException(String mensagem) {
        super(mensagem);
    }
}
