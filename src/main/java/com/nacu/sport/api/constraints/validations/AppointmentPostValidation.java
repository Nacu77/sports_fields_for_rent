package com.nacu.sport.api.constraints.validations;

import com.nacu.sport.api.constraints.validators.AppointmentPostValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target( { TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AppointmentPostValidator.class)
public @interface AppointmentPostValidation
{
    String message() default "Number of applicants must not exceed the number of slots";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
