package dev.pdsf.timewise.model;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class TimeSlot {
    @NotNull
    private String id;
    @NotNull
    private LocalTime start;
    @NotNull
    private LocalTime end;
    private long duration;

    protected TimeSlot() {
    }

    public TimeSlot(String id, String start, String end) {
        this.id = id;
        this.start = LocalTime.parse(start);
        this.end = LocalTime.parse(end);
        this.duration = Duration.between(this.start, this.end).toMinutes();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = LocalTime.parse(start);
        this.setDuration();
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = LocalTime.parse(end);
        this.setDuration();
    }

    private void setDuration() {
        if (this.start == null || this.end == null) {
            return;
        }

        this.duration = Duration.between(this.start, this.end).toMinutes();
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return Objects.equals(id, timeSlot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "id='" + id + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
