package io.github.monthalcantara.agenciaviagem.companhia;

import io.github.monthalcantara.agenciaviagem.pais.Pais;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Companhia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne
    private Pais pais;

    private LocalDateTime instante = LocalDateTime.now();

    @Deprecated
    public Companhia() {
    }

    public Companhia(@NotBlank String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }
}
