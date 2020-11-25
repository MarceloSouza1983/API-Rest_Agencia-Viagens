package io.github.monthalcantara.agenciaviagem.pais;

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
@RequestMapping("/paises")
public class CadastraPaisController {

    private EntityManager manager;

    public CadastraPaisController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity criaNovoPais(@RequestBody @Valid NovoPaisRequest novoPaisRequest, UriComponentsBuilder builder) {
        Pais pais = novoPaisRequest.toModel();
        manager.persist(pais);

        URI uri = builder.path("/paises/{id}").buildAndExpand(pais.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
