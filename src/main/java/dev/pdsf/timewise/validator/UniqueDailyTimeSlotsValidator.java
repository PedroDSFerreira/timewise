package dev.pdsf.timewise.validator;

import dev.pdsf.timewise.model.DailyTimeSlots;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueDailyTimeSlotsValidator implements ConstraintValidator<UniqueDailyTimeSlots, List<DailyTimeSlots>> {

    @Override
    public void initialize(UniqueDailyTimeSlots constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<DailyTimeSlots> value, ConstraintValidatorContext context) {
        Set<DayOfWeek> uniqueDays = new HashSet<>();
        for (DailyTimeSlots slots : value) {
            DayOfWeek day = slots.getDayOfWeek();
            if (uniqueDays.contains(day)) {
                return false; // If the same day occurs more than once, it's invalid
            }
            uniqueDays.add(day);
        }

        return uniqueDays.size() == 7;
    }
}
