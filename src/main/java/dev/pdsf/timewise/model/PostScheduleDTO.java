package dev.pdsf.timewise.model;

import dev.pdsf.timewise.validator.UniqueDailyTimeSlots;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Objects;

public class PostScheduleDTO {
    @Valid
    @NotEmpty
    @UniqueDailyTimeSlots
    private List<DailyTimeSlots> timeSlots;
    @Valid
    @NotEmpty
    private List<Task> tasks;

    protected PostScheduleDTO() {
    }

    public PostScheduleDTO(List<DailyTimeSlots> timeSlots, List<Task> tasks) {
        this.timeSlots = timeSlots;
        this.tasks = tasks;
    }

    public List<DailyTimeSlots> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<DailyTimeSlots> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostScheduleDTO postScheduleDTO = (PostScheduleDTO) o;
        return Objects.equals(timeSlots, postScheduleDTO.timeSlots) && Objects.equals(tasks, postScheduleDTO.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeSlots, tasks);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "timeSlots=" + timeSlots +
                ", tasks=" + tasks +
                '}';
    }
}
