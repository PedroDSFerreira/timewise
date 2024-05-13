package dev.pdsf.timewise.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public class TaskFragment {
    @NotNull
    private final String id;
    @NotNull
    private String taskId;
    @NotNull
    @Min(1)
    @Max(10)
    private int priority;

    protected TaskFragment() {
        this.id = UUID.randomUUID().toString();
    }

    public TaskFragment(String taskId, int priority) {
        this.id = UUID.randomUUID().toString();
        this.taskId = taskId;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskFragment that = (TaskFragment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TaskFragment{" +
               "id='" + id + '\'' +
               ", taskId='" + taskId + '\'' +
               ", priority=" + priority +
               '}';
    }
}
