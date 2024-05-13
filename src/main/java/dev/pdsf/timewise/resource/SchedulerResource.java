package dev.pdsf.timewise.resource;

import dev.pdsf.timewise.model.PostScheduleDTO;
import dev.pdsf.timewise.model.TaskAssignment;
import dev.pdsf.timewise.service.SchedulerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class SchedulerResource {
    private final SchedulerService schedulerService;

    public SchedulerResource(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping("/schedule")
    List<TaskAssignment> create_schedule(@RequestBody @Valid PostScheduleDTO postScheduleDTO) {
        schedulerService.processSchedule(postScheduleDTO);
        return schedulerService.createSchedule(postScheduleDTO);
    }
}
