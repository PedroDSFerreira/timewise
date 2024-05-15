package dev.pdsf.timewise.model;

import dev.pdsf.timewise.model.domain.TaskFragmentAssignment;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class ScheduledSlot {
    private String name;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    protected ScheduledSlot() {
    }

    public ScheduledSlot(TaskFragmentAssignment taskFragmentAssignment) {
        this.name = taskFragmentAssignment.getTaskFragment().getTask().getName();
        this.dayOfWeek = taskFragmentAssignment.getTimeGrain().getTimeSlot().getDayOfWeek();
        this.startTime = taskFragmentAssignment.getTimeGrain().getTimeSlot().getStartTime();
        this.endTime = getEndTime(taskFragmentAssignment.getTimeGrain().getTimeSlot().getStartTime(),
                taskFragmentAssignment.getTaskFragment().getTask().getDuration());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    private LocalTime getEndTime(LocalTime startTime, long duration) {
        return startTime.plusMinutes(duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduledSlot that = (ScheduledSlot) o;
        return Objects.equals(name, that.name) && dayOfWeek == that.dayOfWeek && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dayOfWeek, startTime, endTime);
    }

    @Override
    public String toString() {
        return "ScheduledSlot{" +
                "name='" + name + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
