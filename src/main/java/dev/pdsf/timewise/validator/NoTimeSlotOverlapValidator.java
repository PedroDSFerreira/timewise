package dev.pdsf.timewise.validator;

import dev.pdsf.timewise.model.domain.TimeSlot;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.DayOfWeek;
import java.util.List;

public class NoTimeSlotOverlapValidator implements ConstraintValidator<NoTimeSlotOverlap, List<TimeSlot>> {

    @Override
    public void initialize(NoTimeSlotOverlap constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<TimeSlot> timeSlots, ConstraintValidatorContext context) {
        if (timeSlots == null || timeSlots.isEmpty()) {
            return true;
        }

        for (DayOfWeek day : DayOfWeek.values()) {
            if (hasOverlappingTimeSlotsForDay(timeSlots, day)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasOverlappingTimeSlotsForDay(List<TimeSlot> timeSlots, DayOfWeek day) {
        List<TimeSlot> timeSlotsForDay = getTimeSlotsForDay(timeSlots, day);

        for (int i = 0; i < timeSlotsForDay.size() - 1; i++) {
            TimeSlot current = timeSlotsForDay.get(i);
            for (int j = i + 1; j < timeSlotsForDay.size(); j++) {
                TimeSlot next = timeSlotsForDay.get(j);
                if (timeSlotsOverlap(current, next)) {
                    return true;
                }
            }
        }

        return false;
    }

    private List<TimeSlot> getTimeSlotsForDay(List<TimeSlot> timeSlots, DayOfWeek day) {
        return timeSlots.stream()
                .filter(timeSlot -> timeSlot.getDayOfWeek() == day)
                .toList();
    }

    private boolean timeSlotsOverlap(TimeSlot timeSlot1, TimeSlot timeSlot2) {
        return timeSlot1.getEndTime().isAfter(timeSlot2.getStartTime()) &&
                timeSlot2.getEndTime().isAfter(timeSlot1.getStartTime());
    }
}
