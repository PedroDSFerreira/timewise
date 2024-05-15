package dev.pdsf.timewise.model.domain;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import dev.pdsf.timewise.model.TimeSlot;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public class TimeGrain {
    @NotNull
    @PlanningId
    private final UUID id;
    @NotNull
    private TimeSlot timeSlot;

    protected TimeGrain() {
        this.id = UUID.randomUUID();
    }

    public TimeGrain(TimeSlot timeSlot) {
        this.id = UUID.randomUUID();
        this.timeSlot = timeSlot;
    }

    public UUID getId() {
        return id;
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
        TimeGrain timeGrain = (TimeGrain) o;
        return Objects.equals(id, timeGrain.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TimeGrain(" + timeSlot + ")";
    }
}
