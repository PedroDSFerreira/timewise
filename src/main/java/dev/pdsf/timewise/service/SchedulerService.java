package dev.pdsf.timewise.service;

import ai.timefold.solver.core.api.solver.SolverJob;
import ai.timefold.solver.core.api.solver.SolverManager;
import dev.pdsf.timewise.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class SchedulerService {
    @Autowired
    private SolverManager<Schedule, UUID> solverManager;

    public void processSchedule(PostScheduleDTO postScheduleDTO) {
        postScheduleDTO.mergeTimeSlots();
    }

    public List<TaskAssignment> createSchedule(PostScheduleDTO postScheduleDTO) {
        List<TimeGrain> timeGrains = postScheduleDTO.convertToTimeGrains();
        List<TaskFragment> taskFragments = postScheduleDTO.convertToTaskFragments();

        Schedule problem = new Schedule(timeGrains, taskFragments);

        UUID problemId = UUID.randomUUID();
        SolverJob<Schedule, UUID> solverJob = solverManager.solve(problemId, problem);
        Schedule solution;
        try {
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        return solution.getTaskAssignments();
    }
}
