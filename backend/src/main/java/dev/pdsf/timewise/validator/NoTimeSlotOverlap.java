package dev.pdsf.timewise.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoTimeSlotOverlapValidator.class)
public @interface NoTimeSlotOverlap {

    String message() default "Time slots must not overlap within each day";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
