package io.github.monthalcantara.agenciaviagem.rota;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.monthalcantara.agenciaviagem.aeroporto.Aeroporto;
import io.github.monthalcantara.agenciaviagem.companhia.ExisteId;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaRotaRequest {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nome;

    @NotNull
    @ExisteId(classe = Aeroporto.class, atributo = "id", message = "Não foi encontrado um País de origem com o id informado")
    private Long idAeroportoOrigem;

    @NotNull
    @ExisteId(classe = Aeroporto.class, atributo = "id", message = "Não foi encontrado um País de destino com o id informado")
    private Long idAeroportoDestino;

    @NotNull
    @Positive
    private double duracaoVoo;

    @Deprecated
    public NovaRotaRequest() {
    }

    public NovaRotaRequest(String nome, @NotBlank Long idAeroportoOrigem, @NotBlank Long getIdAeroportoDestino, double duracaoVoo) {
        this.nome = nome;
        this.idAeroportoOrigem = idAeroportoOrigem;
        this.idAeroportoDestino = getIdAeroportoDestino;
        this.duracaoVoo = duracaoVoo;
    }

    public Rota toModel(EntityManager manager) {
        Aeroporto origem = manager.find(Aeroporto.class, this.idAeroportoOrigem);
        Assert.notNull(origem, "Erro interno. Um id desconhecido de aeroporto de Origem passou no filtro");
        Aeroporto destino= manager.find(Aeroporto.class, this.idAeroportoDestino);
        Assert.notNull(origem, "Erro interno. Um id desconhecido de aeroporto de Destino passou no filtro");
        if(destino == origem){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aeroporto de Destino e Origem precisam ser diferentes");
        }
        if(this.nome == null){
            this.nome = origem.getNome() + "-" + destino.getNome();
        }
        return new Rota(this.nome, origem, destino);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdAeroportoOrigem() {
        return idAeroportoOrigem;
    }

    public Long getIdAeroportoDestino() {
        return idAeroportoDestino;
    }

    public double getDuracaoVoo() {
        return duracaoVoo;
    }
}
