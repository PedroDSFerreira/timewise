package dev.pdsf.timewise.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import ai.timefold.solver.core.api.score.stream.Joiners;
import dev.pdsf.timewise.model.TaskAssignment;

public class ScheduleConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                timeGrainConflict(constraintFactory),
                // Soft constraints
        };
    }

    private Constraint timeGrainConflict(ConstraintFactory constraintFactory) {
        // A time grain can only be assigned to one task assignment
        return constraintFactory
                .forEachUniquePair(TaskAssignment.class,
                        Joiners.equal(TaskAssignment::getTimeGrain))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("timeGrainConflict");
    }

}
