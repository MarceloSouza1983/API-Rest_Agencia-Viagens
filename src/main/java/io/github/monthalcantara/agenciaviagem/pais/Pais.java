package io.github.monthalcantara.agenciaviagem.pais;

import io.github.monthalcantara.agenciaviagem.aeroporto.Aeroporto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "pais")
    private List<Aeroporto> aeroportos;

    @Deprecated
    public Pais() {
    }

    public Pais(@NotBlank String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public List<Aeroporto> getAeroportos() {
        return Collections.unmodifiableList(aeroportos);
    }
}
