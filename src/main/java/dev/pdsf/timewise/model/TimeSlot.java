package dev.pdsf.timewise.model;

import dev.pdsf.timewise.validator.ValidTimeSlot;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@ValidTimeSlot
public class TimeSlot {
    @NotNull
    private final UUID id;
    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;

    protected TimeSlot() {
        this.id = UUID.randomUUID();
    }

    public TimeSlot(String dayOfWeek, String startTime, String endTime) {
        this.id = UUID.randomUUID();
        this.dayOfWeek = parseDayOfWeek(dayOfWeek);
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }

    public UUID getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = parseDayOfWeek(dayOfWeek);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime);
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEnd(String endTime) {
        this.endTime = LocalTime.parse(endTime);
    }

    private DayOfWeek parseDayOfWeek(String dayOfWeek) {
        return switch (dayOfWeek.toLowerCase(Locale.ROOT)) {
            case "monday" -> DayOfWeek.MONDAY;
            case "tuesday" -> DayOfWeek.TUESDAY;
            case "wednesday" -> DayOfWeek.WEDNESDAY;
            case "thursday" -> DayOfWeek.THURSDAY;
            case "friday" -> DayOfWeek.FRIDAY;
            case "saturday" -> DayOfWeek.SATURDAY;
            case "sunday" -> DayOfWeek.SUNDAY;
            default -> throw new IllegalArgumentException("Invalid day of week: " + dayOfWeek);
        };
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
                ", start=" + startTime +
                ", end=" + endTime +
                '}';
    }
}
