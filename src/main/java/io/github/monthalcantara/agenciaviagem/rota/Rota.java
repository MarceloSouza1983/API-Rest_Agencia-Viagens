package io.github.monthalcantara.agenciaviagem.rota;

import io.github.monthalcantara.agenciaviagem.aeroporto.Aeroporto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @OneToOne
    private Aeroporto aeroportoOrigem;

    @OneToOne
    private Aeroporto aeroportoDestino;

    @Deprecated
    public Rota() {
    }

    public Rota(@NotBlank String nome, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino) {
        this.nome = nome;
        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
    }

    public Long getId() {
        return id;
    }
}
