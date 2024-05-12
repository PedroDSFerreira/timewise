package dev.pdsf.timewise.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public class Task {
    @NotNull
    private final String id;
    @NotNull
    private String name;
    @NotNull
    @Min(1)
    @Max(10)
    private int priority;
    @NotNull
    private long duration;

    protected Task() {
        this.id = UUID.randomUUID().toString();
    }

    public Task(String name, int priority, long duration) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.priority = priority;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", duration=" + duration +
                '}';
    }
}
