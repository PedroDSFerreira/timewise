package dev.pdsf.timewise.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import dev.pdsf.timewise.model.domain.Task;

import static ai.timefold.solver.core.api.score.stream.ConstraintCollectors.sum;

public class ScheduleConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                tasksFitIntoTimeSlot(constraintFactory),
                // Soft constraints
                earliestStartTime(constraintFactory),
                earliestDay(constraintFactory),
                highestPriority(constraintFactory),
        };
    }

    private Constraint tasksFitIntoTimeSlot(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Task.class)
                .groupBy(Task::getTimeSlot,
                        sum(Task::getDuration))
                .filter((timeSlot, taskDurationSum) -> taskDurationSum > timeSlot.getDuration())
                .penalize(HardSoftScore.ONE_HARD,
                        (timeSlot, taskDurationSum) -> taskDurationSum - timeSlot.getDuration())
                .asConstraint("Tasks must fit into their assigned time slot");
    }


    private Constraint earliestStartTime(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Task.class)
                .reward(HardSoftScore.ofSoft(2),
                        task -> (24 * 60 * 60) - task.getTimeSlot().getStartTime().toSecondOfDay())
                .asConstraint("A task should be assigned to the earliest possible time grain");
    }

    private Constraint earliestDay(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Task.class)
                .reward(HardSoftScore.ofSoft(3),
                        task -> 7 - task.getTimeSlot().getDayOfWeek().getValue())
                .asConstraint("A task should be assigned to the earliest possible day");
    }

    private Constraint highestPriority(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Task.class)
                .reward(HardSoftScore.ONE_SOFT,
                        task -> Task.MAX_PRIORITY - task.getPriority())
                .asConstraint("A task should be assigned to the highest priority (lowest number)");
    }
}
