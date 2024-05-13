package dev.pdsf.timewise.model;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;
import java.util.Objects;

@PlanningSolution
public class Schedule {
    @ProblemFactCollectionProperty
    @ValueRangeProvider
    private List<TimeGrain> timeGrains;
    @ProblemFactCollectionProperty
    @ValueRangeProvider
    private List<TaskFragment> taskFragments;
    @PlanningEntityCollectionProperty
    private List<TaskAssignment> taskAssignments;
    @PlanningScore
    private HardSoftScore score;

    protected Schedule() {
    }

    public Schedule(List<TimeGrain> timeGrains, List<TaskFragment> taskFragments) {
        this.timeGrains = timeGrains;
        this.taskFragments = taskFragments;
        this.taskAssignments = generateTaskAssignments();
    }

    private List<TaskAssignment> generateTaskAssignments() {
        int taskAssignmentSize = taskFragments.size();
        List<TaskAssignment> taskAssignments = new java.util.ArrayList<>(taskAssignmentSize);
        for (TaskFragment taskFragment : taskFragments) {
            TaskAssignment taskAssignment = new TaskAssignment();
            taskAssignment.setTaskFragment(taskFragment);
            taskAssignments.add(taskAssignment);
        }
        return taskAssignments;
    }

    public List<TimeGrain> getTimeGrains() {
        return timeGrains;
    }

    public void setTimeGrains(List<TimeGrain> timeGrains) {
        this.timeGrains = timeGrains;
    }

    public List<TaskFragment> getTaskFragments() {
        return taskFragments;
    }

    public void setTaskFragments(List<TaskFragment> taskFragments) {
        this.taskFragments = taskFragments;
    }

    public List<TaskAssignment> getTaskAssignments() {
        return taskAssignments;
    }

    public void setTaskAssignments(List<TaskAssignment> taskAssignments) {
        this.taskAssignments = taskAssignments;
    }

    public HardSoftScore getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(timeGrains, schedule.timeGrains) && Objects.equals(taskFragments, schedule.taskFragments) && Objects.equals(taskAssignments, schedule.taskAssignments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeGrains, taskFragments, taskAssignments);
    }

    @Override
    public String toString() {
        return "Schedule{" +
               "timeGrains=" + timeGrains +
               ", taskFragments=" + taskFragments +
               ", taskAssignments=" + taskAssignments +
               '}';
    }
}
