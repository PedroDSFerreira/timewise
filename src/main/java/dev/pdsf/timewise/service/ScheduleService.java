package dev.pdsf.timewise.service;

import ai.timefold.solver.core.api.solver.SolverJob;
import ai.timefold.solver.core.api.solver.SolverManager;
import dev.pdsf.timewise.model.PostScheduleDTO;
import dev.pdsf.timewise.model.domain.ScheduleDomain;
import dev.pdsf.timewise.model.domain.TaskFragment;
import dev.pdsf.timewise.model.domain.TaskFragmentAssignment;
import dev.pdsf.timewise.model.domain.TimeGrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ScheduleService {
    @Autowired
    private SolverManager<ScheduleDomain, UUID> solverManager;

    public void processSchedule(PostScheduleDTO postScheduleDTO) {
        postScheduleDTO.mergeTimeSlots();
    }

    public List<TaskFragmentAssignment> createSchedule(PostScheduleDTO postScheduleDTO) {
        List<TimeGrain> timeGrains = postScheduleDTO.convertToTimeGrains();
        List<TaskFragment> taskFragments = postScheduleDTO.convertToTaskFragments();

        ScheduleDomain problem = new ScheduleDomain(timeGrains, taskFragments);

        UUID problemId = UUID.randomUUID();
        SolverJob<ScheduleDomain, UUID> solverJob = solverManager.solve(problemId, problem);
        ScheduleDomain solution;
        try {
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        return solution.getTaskAssignments();
    }
}
