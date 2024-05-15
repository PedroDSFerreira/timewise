package dev.pdsf.timewise.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import ai.timefold.solver.core.api.score.stream.Joiners;
import dev.pdsf.timewise.model.domain.TaskFragmentAssignment;

public class ScheduleConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                timeGrainConflict(constraintFactory),
                taskFragmentsAreContiguous(constraintFactory),
                // Soft constraints
        };
    }

    private Constraint timeGrainConflict(ConstraintFactory constraintFactory) {
        // A time grain can only be assigned to one task assignment
        return constraintFactory
                .forEachUniquePair(TaskFragmentAssignment.class,
                        Joiners.equal(TaskFragmentAssignment::getTimeGrain))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("timeGrainConflict");
    }

    private Constraint taskFragmentsAreContiguous(ConstraintFactory constraintFactory) {
        // compare taskFragment.getTaskId and timeGrain.getTimeSlotId
        return constraintFactory
                .forEachUniquePair(TaskFragmentAssignment.class)
                .filter(
                        (ta1, ta2) -> ta1.getTaskFragment().getTask().getId()
                                .equals(ta2.getTaskFragment().getTask().getId()) &&
                                !ta1.getTimeGrain().getTimeSlot().getId()
                                        .equals(ta2.getTimeGrain().getTimeSlot().getId()))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("taskFragmentsAreContiguous");
    }

    //private Constraint taskPriority(ConstraintFactory constraintFactory) {
    //    // A task assignment with a higher priority should be assigned to a time grain before a task assignment with a lower priority
    //    return constraintFactory
    //            .forEachUniquePair(TaskFragmentAssignment.class)
    //            .filter(
    //                    (ta1, ta2) -> ta1.getTaskFragment().getPriority()
    //                                          > ta2.getTaskFragment().getPriority())
    //            .penalize(HardSoftScore.ONE_SOFT)
    //            .asConstraint("taskPriority");
    //}
}
