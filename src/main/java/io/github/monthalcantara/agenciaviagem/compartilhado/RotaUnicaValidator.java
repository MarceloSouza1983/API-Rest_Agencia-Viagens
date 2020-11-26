package io.github.monthalcantara.agenciaviagem.compartilhado;

import io.github.monthalcantara.agenciaviagem.aeroporto.Aeroporto;
import io.github.monthalcantara.agenciaviagem.rota.NovaRotaRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

public class RotaUnicaValidator implements Validator {

    private EntityManager manager;

    public RotaUnicaValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaRotaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    @Transactional
    public void validate(Object value, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NovaRotaRequest request = (NovaRotaRequest) value;

        Long idAeroportoOrigem = request.getIdAeroportoOrigem();
        Aeroporto origem = manager.find(Aeroporto.class, idAeroportoOrigem);

        Long idAeroportoDestino = request.getIdAeroportoDestino();
        Aeroporto destino = manager.find(Aeroporto.class, idAeroportoDestino);

        Query query = manager.createQuery("Select 1 from Rota where aeroportoOrigem =:origem and aeroportoDestino =:destino");
        query.setParameter("origem", origem);
        query.setParameter("destino", destino);

        if (!query.getResultList().isEmpty()) {
            errors.rejectValue("idAeroportoOrigem", null, "Ja existe uma rota com destino e origem iguais cadastrado");
        }
    }
}
