package io.github.monthalcantara.agenciaviagem.compartilhado;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

    String message() default "{io.github.monthalcantara.beanvalidation.uniquevalue}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> classe();

    String atributo();
}
