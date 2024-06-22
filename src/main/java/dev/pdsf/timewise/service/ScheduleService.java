package dev.pdsf.timewise.service;

import ai.timefold.solver.core.api.solver.SolverJob;
import ai.timefold.solver.core.api.solver.SolverManager;
import dev.pdsf.timewise.model.PostScheduleDTO;
import dev.pdsf.timewise.model.domain.Schedule;
import dev.pdsf.timewise.model.domain.Task;
import dev.pdsf.timewise.model.domain.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ScheduleService {
    @Autowired
    private SolverManager<Schedule, UUID> solverManager;

    public void processSchedule(PostScheduleDTO postScheduleDTO) {
        postScheduleDTO.mergeTimeSlots();
    }

    public List<Task> createSchedule(PostScheduleDTO postScheduleDTO) {
        List<TimeSlot> timeSlots = postScheduleDTO.getTimeSlots();
        List<Task> tasks = postScheduleDTO.getTasks();

        Schedule problem = new Schedule(timeSlots, tasks);

        UUID problemId = UUID.randomUUID();
        SolverJob<Schedule, UUID> solverJob = solverManager.solve(problemId, problem);
        Schedule solution;
        try {
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        return solution.getTasks();
    }
}
