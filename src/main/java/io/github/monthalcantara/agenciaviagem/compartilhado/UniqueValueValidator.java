package io.github.monthalcantara.agenciaviagem.compartilhado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String atributo;
    private Class<?> classe;

    @PersistenceContext
    private EntityManager manager;

    public UniqueValueValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.classe = constraintAnnotation.classe();
        this.atributo = constraintAnnotation.atributo();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("Select 1 from " + classe.getSimpleName() + " where " + atributo + " =:value");
        query.setParameter("value", value);
        return query.getResultList().isEmpty();
    }
}
