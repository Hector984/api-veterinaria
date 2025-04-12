package com.veterinaria.api_veterinaria.validators.usuario;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = EmailUnicoValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailUnico {

    String message() default "ya está en uso";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
