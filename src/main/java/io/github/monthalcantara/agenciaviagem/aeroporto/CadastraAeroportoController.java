package io.github.monthalcantara.agenciaviagem.aeroporto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/aeroportos")
public class CadastraAeroportoController {

    private EntityManager manager;

    public CadastraAeroportoController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity criaAeroporto(@RequestBody @Valid NovoAeroportoRequest novoAeroportoRequest, UriComponentsBuilder builder) {
        Aeroporto aeroporto = novoAeroportoRequest.toModel(manager);
        manager.persist(aeroporto);
        URI uri = builder.path("/aeroportos/{id}").buildAndExpand(aeroporto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
