package dev.pdsf.timewise.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.*;
import dev.pdsf.timewise.model.domain.Task;
import dev.pdsf.timewise.model.domain.TimeSlot;

public class ScheduleConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                taskFitsIntoTimeSlot(constraintFactory),
                // Soft constraints
                earliestStartTime(constraintFactory),
                earliestDay(constraintFactory),
                highestPriority(constraintFactory)
        };
    }

    private Constraint taskFitsIntoTimeSlot(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(TimeSlot.class)
                .join(Task.class,
                        Joiners.equal(TimeSlot::getId, task -> task.getTimeSlot().getId()))
                .groupBy((timeSlot, task) -> timeSlot,
                        ConstraintCollectors.sum((timeSlot, task) -> Math.toIntExact(task.getDuration())))
                .filter((timeSlot, taskDurationSum) -> taskDurationSum > timeSlot.getDuration())
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("A task should fit into a time slot");
    }


    private Constraint earliestStartTime(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Task.class)
                .filter(
                        (ta1, ta2) -> ta1.getTimeSlot().getStartTime()
                                .isAfter(ta2.getTimeSlot().getStartTime()))
                .penalize(HardSoftScore.ONE_SOFT)
                .asConstraint("A task assignment should be assigned to the earliest possible time grain");
    }

    private Constraint earliestDay(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Task.class)
                .filter(
                        (ta1, ta2) -> ta1.getTimeSlot().getDayOfWeek()
                                .getValue() > ta2.getTimeSlot().getDayOfWeek().getValue())
                .penalize(HardSoftScore.ONE_SOFT)
                .asConstraint("A task assignment should be assigned to the earliest possible day");
    }

    private Constraint highestPriority(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Task.class)
                .filter(
                        (ta1, ta2) -> ta1.getPriority() > ta2.getPriority())
                .penalize(HardSoftScore.ONE_SOFT)
                .asConstraint("A task assignment should be assigned to the highest priority (lowest number)");
    }
}
