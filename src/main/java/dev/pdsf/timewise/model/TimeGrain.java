package dev.pdsf.timewise.model;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class TimeGrain {
    @NotNull
    @PlanningId
    private final String id;
    @NotNull
    private String timeSlotId;
    @NotNull
    private LocalTime start;
    @NotNull
    private DayOfWeek dayOfWeek;

    protected TimeGrain() {
        this.id = UUID.randomUUID().toString();
    }

    public TimeGrain(String timeSlotId, LocalTime start, DayOfWeek dayOfWeek) {
        this.id = UUID.randomUUID().toString();
        this.timeSlotId = timeSlotId;
        this.start = start;
        this.dayOfWeek = dayOfWeek;
    }

    public String getId() {
        return id;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public String getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(String timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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


}
