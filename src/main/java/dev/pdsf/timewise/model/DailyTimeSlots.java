package dev.pdsf.timewise.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DailyTimeSlots {
    @NotNull
    private String id;
    @NotNull
    private DayOfWeek dayOfWeek;
    @Valid
    private List<TimeSlot> timeSlots;

    protected DailyTimeSlots() {
    }

    public DailyTimeSlots(String id, String dayOfWeek, List<TimeSlot> timeSlots) {
        this.id = id;
        this.dayOfWeek = parseDayOfWeek(dayOfWeek);
        this.timeSlots = timeSlots;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = parseDayOfWeek(dayOfWeek);
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
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
        DailyTimeSlots that = (DailyTimeSlots) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DailyTimeSlots{" +
                "id='" + id + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                ", timeSlots=" + timeSlots +
                '}';
    }
}
