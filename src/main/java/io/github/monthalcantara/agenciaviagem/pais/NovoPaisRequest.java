package io.github.monthalcantara.agenciaviagem.pais;

import javax.validation.constraints.NotBlank;

public class NovoPaisRequest {

    @NotBlank
    private String nome;

    public Pais toModel() {
    return new Pais(this.nome);
    }

    public String getNome() {
        return nome;
    }
}
