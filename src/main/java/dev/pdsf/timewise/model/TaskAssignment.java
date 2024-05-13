package dev.pdsf.timewise.model;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;

import java.util.Objects;
import java.util.UUID;

@PlanningEntity
public class TaskAssignment {
    @PlanningId
    private final String id;
    @PlanningVariable
    private TimeGrain timeGrain;
    private TaskFragment taskFragment;

    protected TaskAssignment() {
        this.id = UUID.randomUUID().toString();
    }

    public TaskAssignment(TaskFragment taskFragment, TimeGrain timeGrain) {
        this.id = UUID.randomUUID().toString();
        this.taskFragment = taskFragment;
        this.timeGrain = timeGrain;
    }

    public String getId() {
        return id;
    }

    public TaskFragment getTaskFragment() {
        return taskFragment;
    }

    public void setTaskFragment(TaskFragment taskFragment) {
        this.taskFragment = taskFragment;
    }

    public TimeGrain getTimeGrain() {
        return timeGrain;
    }

    public void setTimeGrain(TimeGrain timeGrain) {
        this.timeGrain = timeGrain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskAssignment that = (TaskAssignment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TaskAssignment{" +
               "id='" + id + '\'' +
               ", taskFragment=" + taskFragment +
               ", timeGrain=" + timeGrain +
               '}';
    }
}
