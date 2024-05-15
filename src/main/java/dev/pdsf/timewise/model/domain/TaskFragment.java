package dev.pdsf.timewise.model.domain;

import dev.pdsf.timewise.model.Task;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public class TaskFragment {
    @NotNull
    private final UUID id;
    @NotNull
    private Task task;

    protected TaskFragment() {
        this.id = UUID.randomUUID();
    }

    public TaskFragment(Task task) {
        this.id = UUID.randomUUID();
        this.task = task;
    }

    public UUID getId() {
        return id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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
        return "TaskFragment(" + task + ")";
    }
}
