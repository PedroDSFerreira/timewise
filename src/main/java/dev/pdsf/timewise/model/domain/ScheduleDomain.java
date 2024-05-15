package dev.pdsf.timewise.model.domain;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;
import java.util.Objects;

@PlanningSolution
public class ScheduleDomain {
    @ProblemFactCollectionProperty
    @ValueRangeProvider
    private List<TimeGrain> timeGrains;
    @ProblemFactCollectionProperty
    @ValueRangeProvider
    private List<TaskFragment> taskFragments;
    @PlanningEntityCollectionProperty
    private List<TaskFragmentAssignment> taskFragmentAssignments;
    @PlanningScore
    private HardSoftScore score;

    protected ScheduleDomain() {
    }

    public ScheduleDomain(List<TimeGrain> timeGrains, List<TaskFragment> taskFragments) {
        this.timeGrains = timeGrains;
        this.taskFragments = taskFragments;
        this.taskFragmentAssignments = generateTaskAssignments();
    }

    private List<TaskFragmentAssignment> generateTaskAssignments() {
        int taskAssignmentSize = taskFragments.size();
        List<TaskFragmentAssignment> taskFragmentAssignments = new java.util.ArrayList<>(taskAssignmentSize);
        for (TaskFragment taskFragment : taskFragments) {
            TaskFragmentAssignment taskFragmentAssignment = new TaskFragmentAssignment();
            taskFragmentAssignment.setTaskFragment(taskFragment);
            taskFragmentAssignments.add(taskFragmentAssignment);
        }
        return taskFragmentAssignments;
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

    public List<TaskFragmentAssignment> getTaskAssignments() {
        return taskFragmentAssignments;
    }

    public void setTaskAssignments(List<TaskFragmentAssignment> taskFragmentAssignments) {
        this.taskFragmentAssignments = taskFragmentAssignments;
    }

    public HardSoftScore getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleDomain scheduleDomain = (ScheduleDomain) o;
        return Objects.equals(timeGrains, scheduleDomain.timeGrains) && Objects.equals(taskFragments, scheduleDomain.taskFragments) && Objects.equals(taskFragmentAssignments, scheduleDomain.taskFragmentAssignments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeGrains, taskFragments, taskFragmentAssignments);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "timeGrains=" + timeGrains +
                ", taskFragments=" + taskFragments +
                ", taskFragmentAssignments=" + taskFragmentAssignments +
                '}';
    }
}
