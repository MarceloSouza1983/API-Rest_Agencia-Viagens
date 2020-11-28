package io.github.monthalcantara.agenciaviagem.companhia;

import io.github.monthalcantara.agenciaviagem.compartilhado.ExisteId;
import io.github.monthalcantara.agenciaviagem.compartilhado.UniqueValue;
import io.github.monthalcantara.agenciaviagem.pais.Pais;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class NovaCompanhiaRequest {

    @NotBlank
    @UniqueValue(atributo = "nome", classe = Companhia.class, message = "Ja existe uma Companhia registrada com esse nome")
    private String nome;

    @NotNull
    @ExisteId(atributo = "id", classe = Pais.class, message = "Não foi encontrado Pais com o id informado, informe um País válido")
    private Long idPais;

    /**
     * @Deprecated Apenas para uso do Framework
     */
    @Deprecated
    private NovaCompanhiaRequest() {
    }

    public NovaCompanhiaRequest(@NotBlank String nome, @NotNull Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public Companhia toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, this.idPais);
        Assert.state(Optional.ofNullable(pais)
                        .isPresent(),
                "Validação externa falhou e chegou um id sem país aqui no toModel");
        return new Companhia(this.nome, pais);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }
}
