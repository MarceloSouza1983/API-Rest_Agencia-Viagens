package io.github.monthalcantara.agenciaviagem.aeroporto;

import io.github.monthalcantara.agenciaviagem.companhia.ExisteId;
import io.github.monthalcantara.agenciaviagem.compartilhado.UniqueValue;
import io.github.monthalcantara.agenciaviagem.pais.Pais;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoAeroportoRequest {

    @NotBlank
    @UniqueValue(classe = Aeroporto.class, atributo = "nome", message = "Ja existe um aeroporto com esse nome Cadastrado")
    private String nome;

    @NotNull
    @ExisteId(classe = Pais.class, atributo = "id", message = "Não foi encontrado Pais com o id informado, informe um País válido")
    private Long idPais;

    @Deprecated
    public NovoAeroportoRequest() {
    }

    public NovoAeroportoRequest(@NotBlank String nome, @NotNull Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Aeroporto toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, idPais);
        if (pais == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi encontrado País com o id informado");
        }
        return new Aeroporto(this.nome, pais);

    }
}
