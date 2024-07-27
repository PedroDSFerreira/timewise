package dev.pdsf.timewise.model.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@PlanningEntity
public class Task {
    public static final int MAX_PRIORITY = 10;

    @PlanningId
    @NotNull
    private final UUID id;
    @NotNull
    private String name;
    @NotNull
    @Min(1)
    @Max(MAX_PRIORITY)
    private int priority;
    @NotNull
    private int duration;

    @PlanningVariable(allowsUnassigned = true)
    private TimeSlot timeSlot;

    protected Task() {
        this.id = UUID.randomUUID();
    }

    public Task(String name, int priority, int duration) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.priority = priority;
        this.duration = duration;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Task(" + id + ',' + name + ',' + duration + ')';
    }
}
