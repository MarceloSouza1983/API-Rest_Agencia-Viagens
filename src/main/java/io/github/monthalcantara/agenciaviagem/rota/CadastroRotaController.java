package io.github.monthalcantara.agenciaviagem.rota;

import io.github.monthalcantara.agenciaviagem.compartilhado.RotaUnicaValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/rotas")
public class CadastroRotaController {

    private EntityManager manager;

    public CadastroRotaController(EntityManager manager) {
        this.manager = manager;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(new RotaUnicaValidator(manager));
    }

    @PostMapping
    @Transactional
    public ResponseEntity criaRota(@RequestBody @Valid NovaRotaRequest novaRotaRequest, UriComponentsBuilder builder) {
        Rota rota = novaRotaRequest.toModel(manager);
        manager.persist(rota);
        URI uri = builder.path("/rotas/{id}").buildAndExpand(rota.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
