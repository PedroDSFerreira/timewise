package dev.pdsf.timewise.model;

import dev.pdsf.timewise.model.domain.Task;
import dev.pdsf.timewise.model.domain.TimeSlot;
import dev.pdsf.timewise.validator.NoTimeSlotOverlap;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class PostScheduleRequestDTO {
    @Valid
    @NotEmpty
    @NoTimeSlotOverlap
    private List<TimeSlot> timeSlots;
    @Valid
    @NotEmpty
    private List<Task> tasks;

    protected PostScheduleRequestDTO() {
    }

    public PostScheduleRequestDTO(List<TimeSlot> timeSlots, List<Task> tasks) {
        this.timeSlots = timeSlots;
        this.tasks = tasks;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public void mergeTimeSlots() {
        timeSlots.sort(Comparator.comparing(TimeSlot::getDayOfWeek).thenComparing(TimeSlot::getStartTime));

        List<TimeSlot> mergedTimeSlots = new ArrayList<>();
        for (TimeSlot current : timeSlots) {
            if (!mergedTimeSlots.isEmpty()) {
                TimeSlot last = mergedTimeSlots.getLast();

                if (isSameDayOfWeek(last, current) && isContiguous(last, current)) {
                    last.setEndTime(current.getEndTime());
                } else {
                    mergedTimeSlots.add(current);
                }
            } else {
                mergedTimeSlots.add(current);
            }
        }

        timeSlots = mergedTimeSlots;
    }

    private boolean isSameDayOfWeek(TimeSlot ts1, TimeSlot ts2) {
        return ts1.getDayOfWeek().equals(ts2.getDayOfWeek());
    }

    private boolean isContiguous(TimeSlot ts1, TimeSlot ts2) {
        return ts1.getEndTime().equals(ts2.getStartTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostScheduleRequestDTO postScheduleRequestDTO = (PostScheduleRequestDTO) o;
        return Objects.equals(timeSlots, postScheduleRequestDTO.timeSlots) && Objects.equals(tasks, postScheduleRequestDTO.tasks);
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
