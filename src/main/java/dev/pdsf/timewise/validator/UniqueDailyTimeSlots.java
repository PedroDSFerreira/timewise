package dev.pdsf.timewise.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDailyTimeSlotsValidator.class)
public @interface UniqueDailyTimeSlots {
    String message() default "DailyTimeSlots list must contain all days of the week and each day should occur only once";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
