package dev.pdsf.timewise.model;

import dev.pdsf.timewise.model.domain.TaskFragment;
import dev.pdsf.timewise.model.domain.TimeGrain;
import dev.pdsf.timewise.validator.NoTimeSlotOverlap;
import dev.pdsf.timewise.validator.ValidPostScheduleDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static dev.pdsf.timewise.ScheduleConstants.MINUTES_PER_GRAIN;

@ValidPostScheduleDTO
public class PostScheduleDTO {
    @Valid
    @NotEmpty
    @NoTimeSlotOverlap
    private List<TimeSlot> timeSlots;
    @Valid
    @NotEmpty
    private List<Task> tasks;

    protected PostScheduleDTO() {
    }

    public PostScheduleDTO(List<TimeSlot> timeSlots, List<Task> tasks) {
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

    public List<TimeGrain> convertToTimeGrains() {
        List<TimeGrain> timeGrains = new ArrayList<>();
        for (TimeSlot timeSlot : timeSlots) {
            LocalTime startTime = timeSlot.getStartTime();
            LocalTime end = timeSlot.getEndTime();
            while (startTime.isBefore(end)) {
                timeGrains.add(new TimeGrain(timeSlot));
                startTime = startTime.plusMinutes(MINUTES_PER_GRAIN);
            }
        }
        return timeGrains;
    }

    public List<TaskFragment> convertToTaskFragments() {
        List<TaskFragment> taskFragments = new ArrayList<>();
        for (Task task : tasks) {
            long duration = task.getDuration();
            long totalFragments = (long) Math.ceil((double) duration / MINUTES_PER_GRAIN);
            for (int i = 0; i < totalFragments; i++) {
                taskFragments.add(new TaskFragment(task));
            }
        }
        return taskFragments;
    }

    public void mergeTimeSlots() {
        timeSlots.sort(Comparator.comparing(TimeSlot::getDayOfWeek).thenComparing(TimeSlot::getStartTime));

        List<TimeSlot> mergedTimeSlots = new ArrayList<>();
        for (TimeSlot current : timeSlots) {
            if (!mergedTimeSlots.isEmpty()) {
                TimeSlot last = mergedTimeSlots.getLast();

                if (isSameDayOfWeek(last.getDayOfWeek(), current.getDayOfWeek()) &&
                        isContiguous(last.getEndTime(), current.getStartTime())) {
                    last.setEnd(current.getEndTime().toString());
                } else {
                    mergedTimeSlots.add(current);
                }
            } else {
                mergedTimeSlots.add(current);
            }
        }

        timeSlots = mergedTimeSlots;
    }

    private boolean isSameDayOfWeek(DayOfWeek dow1, DayOfWeek dow2) {
        return dow1.equals(dow2);
    }

    private boolean isContiguous(LocalTime end, LocalTime start) {
        return end.equals(start) || end.plusMinutes(1).equals(start);
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
