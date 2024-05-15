package dev.pdsf.timewise.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PostScheduleDTOValidator.class)
public @interface ValidPostScheduleDTO {

    String message() default "The total time of the sum of TimeSlots is less than the total time of the duration of tasks";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
