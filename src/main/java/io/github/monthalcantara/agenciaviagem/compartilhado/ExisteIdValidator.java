package io.github.monthalcantara.agenciaviagem.compartilhado;

import io.github.monthalcantara.agenciaviagem.companhia.ExisteId;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {

    private Class<?> classe;

    private String atributo;

    private EntityManager manager;

    public ExisteIdValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(ExisteId constraintAnnotation) {
        this.classe = constraintAnnotation.classe();
        this.atributo = constraintAnnotation.atributo();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("Select x from " + classe.getSimpleName() + " x where " + atributo + "=:value");
        query.setParameter("value", value);

        return !query.getResultList().isEmpty();
    }
}
