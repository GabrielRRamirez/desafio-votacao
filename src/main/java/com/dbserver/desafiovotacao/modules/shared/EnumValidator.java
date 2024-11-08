package com.dbserver.desafiovotacao.modules.shared;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = EnumValidatorImpl.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {

    Class<? extends Enum<?>> enumClass();

    String message() default "valor inválido!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
