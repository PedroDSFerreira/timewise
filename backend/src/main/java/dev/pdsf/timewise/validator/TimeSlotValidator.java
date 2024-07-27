package dev.pdsf.timewise.validator;

import dev.pdsf.timewise.model.domain.TimeSlot;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

public class TimeSlotValidator implements ConstraintValidator<ValidTimeSlot, TimeSlot> {

    @Override
    public void initialize(ValidTimeSlot constraintAnnotation) {
    }

    @Override
    public boolean isValid(TimeSlot timeSlot, ConstraintValidatorContext context) {
        if (timeSlot == null) {
            return true;
        }

        LocalTime startTime = timeSlot.getStartTime();
        LocalTime endTime = timeSlot.getEndTime();

        return startTime.isBefore(endTime);
    }
}
