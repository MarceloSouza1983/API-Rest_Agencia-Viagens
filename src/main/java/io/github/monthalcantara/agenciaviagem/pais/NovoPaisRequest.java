package io.github.monthalcantara.agenciaviagem.pais;

import io.github.monthalcantara.agenciaviagem.compartilhado.UniqueValue;

import javax.validation.constraints.NotBlank;

public class NovoPaisRequest {

    @NotBlank
    @UniqueValue(classe = Pais.class, atributo = "nome", message = "Já existe um País cadastrado com esse nome")
    private String nome;

    public Pais toModel() {
    return new Pais(this.nome);
    }

    public String getNome() {
        return nome;
    }
}
