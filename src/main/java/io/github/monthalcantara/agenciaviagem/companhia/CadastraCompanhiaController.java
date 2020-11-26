package io.github.monthalcantara.agenciaviagem.companhia;

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
@RequestMapping("/companhias")
public class CadastraCompanhiaController {

    private EntityManager manager;

    public CadastraCompanhiaController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity criaNovaCompainha(@RequestBody @Valid NovaCompanhiaRequest novaCompanhiaRequest, UriComponentsBuilder bilder) {

        Companhia companhia = novaCompanhiaRequest.toModel(manager);
        manager.persist(companhia);
        URI uri = bilder.path("/companhias/{id}").buildAndExpand(companhia.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
