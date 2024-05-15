package dev.pdsf.timewise.validator;

import dev.pdsf.timewise.model.TimeSlot;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

import static dev.pdsf.timewise.ScheduleConstants.MINUTES_PER_GRAIN;

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

        return isTimeInIntervalBucket(startTime) &&
                isTimeInIntervalBucket(endTime) &&
                startTime.isBefore(endTime);
    }

    private boolean isTimeInIntervalBucket(LocalTime time) {
        int minute = time.getMinute();
        return minute % MINUTES_PER_GRAIN == 0;
    }
}
