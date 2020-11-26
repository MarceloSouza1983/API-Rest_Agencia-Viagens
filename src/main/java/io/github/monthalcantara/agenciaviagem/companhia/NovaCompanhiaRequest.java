package io.github.monthalcantara.agenciaviagem.companhia;

import io.github.monthalcantara.agenciaviagem.compartilhado.UniqueValue;
import io.github.monthalcantara.agenciaviagem.pais.Pais;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCompanhiaRequest {

    @NotBlank
    @UniqueValue(atributo = "nome", classe = Companhia.class, message = "Ja existe uma Companhia registrada com esse nome")
    private String nome;

    @NotNull
    private Long idPais;


    public Companhia toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, this.idPais);
        return new Companhia(this.nome, pais);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }
}
