package dev.pdsf.timewise.validator;

import dev.pdsf.timewise.model.PostScheduleDTO;
import dev.pdsf.timewise.model.domain.Task;
import dev.pdsf.timewise.model.domain.TimeSlot;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PostScheduleDTOValidator implements ConstraintValidator<ValidPostScheduleDTO, PostScheduleDTO> {

    @Override
    public void initialize(ValidPostScheduleDTO constraintAnnotation) {
    }

    @Override
    public boolean isValid(PostScheduleDTO postScheduleDTO, ConstraintValidatorContext context) {
        if (postScheduleDTO == null) {
            return true;
        }

        long totalTimeslotDuration = postScheduleDTO.getTimeSlots().stream()
                .mapToLong(TimeSlot::getDuration)
                .sum();

        long totalTaskDuration = postScheduleDTO.getTasks().stream()
                .mapToLong(Task::getDuration)
                .sum();

        return totalTimeslotDuration >= totalTaskDuration;
    }
}
