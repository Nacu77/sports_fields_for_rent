package com.nacu.sport.api.constraints.validations;

import com.nacu.sport.api.constraints.validators.AppointmentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Target({ TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AppointmentValidator.class)
public @interface AppointmentValidation
{
    String message() default "Invalid Appointment";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
