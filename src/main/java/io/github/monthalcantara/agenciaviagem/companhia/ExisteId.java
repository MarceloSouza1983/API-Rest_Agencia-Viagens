package io.github.monthalcantara.agenciaviagem.companhia;

import io.github.monthalcantara.agenciaviagem.compartilhado.ExisteIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExisteIdValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteId {
    String message() default "{io.github.monthalcantara.beanvalidation.existeid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> classe();

    String atributo();
}
