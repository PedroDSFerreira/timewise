package dev.pdsf.timewise.validator;

import dev.pdsf.timewise.model.TimeSlot;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

public class TimeSlotValidator implements ConstraintValidator<ValidTimeSlot, TimeSlot> {

    public static final int MINUTES_PER_GRAIN = 15;

    @Override
    public void initialize(ValidTimeSlot constraintAnnotation) {
    }

    @Override
    public boolean isValid(TimeSlot timeSlot, ConstraintValidatorContext context) {
        if (timeSlot == null) {
            return true;
        }

        LocalTime start = timeSlot.getStart();
        LocalTime end = timeSlot.getEnd();

        return isTimeInIntervalBucket(start) &&
               isTimeInIntervalBucket(end) &&
               start.isBefore(end);
    }

    private boolean isTimeInIntervalBucket(LocalTime time) {
        int minute = time.getMinute();
        return minute % MINUTES_PER_GRAIN == 0;
    }
}
