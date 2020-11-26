package io.github.monthalcantara.agenciaviagem.aeroporto;

import io.github.monthalcantara.agenciaviagem.pais.Pais;

import javax.persistence.*;
import java.util.Objects;

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

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aeroporto aeroporto = (Aeroporto) o;
        return nome.equals(aeroporto.nome) &&
                pais.equals(aeroporto.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, pais);
    }
}
