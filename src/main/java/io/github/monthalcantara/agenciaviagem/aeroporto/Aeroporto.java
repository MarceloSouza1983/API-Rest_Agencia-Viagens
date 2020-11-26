package io.github.monthalcantara.agenciaviagem.aeroporto;

import io.github.monthalcantara.agenciaviagem.pais.Pais;

import javax.persistence.*;

@Entity
public class Aeroporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

    @Deprecated
    public Aeroporto() {
    }

    public Aeroporto(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }
}
