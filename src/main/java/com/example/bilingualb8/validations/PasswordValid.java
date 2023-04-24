package com.example.bilingualb8.validations;

/**
 * Shabdanov Ilim
 **/

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValid {
    String message() default "The password must contain at least one letter in , one number and be at least 8 characters long !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}