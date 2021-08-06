package br.com.mercadolivre.config.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {DiferentNamesValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DiferentNames {

    String message() default "Precisa ter nomes diferentes";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
