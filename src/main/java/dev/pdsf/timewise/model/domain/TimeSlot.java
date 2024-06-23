package dev.pdsf.timewise.model.domain;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import dev.pdsf.timewise.validator.ValidTimeSlot;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@ValidTimeSlot
public class TimeSlot {
    @PlanningId
    @NotNull
    private final UUID id;
    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private int duration;

    protected TimeSlot() {
        this.id = UUID.randomUUID();
    }

    public TimeSlot(String dayOfWeek, String startTime, String endTime) {
        this.id = UUID.randomUUID();
        this.dayOfWeek = parseDayOfWeek(dayOfWeek);
        this.startTime = LocalTime.parse(startTime);
        this.duration = parseDuration(endTime);
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

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalTime getEndTime() {
        return startTime.plusMinutes(duration);
    }

    public void setEndTime(LocalTime endTime) {
        this.duration = (int) Duration.between(this.startTime, endTime).toMinutes();
    }

    private int parseDuration(String endTime) {
        return (int) Duration.between(startTime, LocalTime.parse(endTime)).toMinutes();
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
        return "TimeSlot(" + id + ',' + dayOfWeek + ',' + startTime + ',' + duration + ')';
    }
}
