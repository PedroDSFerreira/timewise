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
    private final String id;
    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull
    private LocalTime start;
    @NotNull
    private LocalTime end;

    protected TimeSlot() {
        this.id = UUID.randomUUID().toString();
    }

    public TimeSlot(String dayOfWeek, String start, String end) {
        this.id = UUID.randomUUID().toString();
        this.dayOfWeek = parseDayOfWeek(dayOfWeek);
        this.start = LocalTime.parse(start);
        this.end = LocalTime.parse(end);
    }

    public String getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = parseDayOfWeek(dayOfWeek);
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = LocalTime.parse(start);
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = LocalTime.parse(end);
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
               ", start=" + start +
               ", end=" + end +
               '}';
    }
}
