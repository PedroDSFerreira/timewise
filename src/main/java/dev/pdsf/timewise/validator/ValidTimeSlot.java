package dev.pdsf.timewise.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeSlotValidator.class)
public @interface ValidTimeSlot {

    String message() default "Start time must be before end time and both must be in {MINUTES_PER_GRAIN} minute intervals";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
