package io.github.monthalcantara.agenciaviagem.exception;

import java.util.Arrays;
import java.util.List;

public class ApiErro {

    private List<String> mensagem;

    public ApiErro(List<String> mensagem) {
        this.mensagem = mensagem;
    }

    public ApiErro(String mensagem){
        this.mensagem = Arrays.asList(mensagem);
    }

    public List<String> getMensagem() {
        return mensagem;
    }
}
