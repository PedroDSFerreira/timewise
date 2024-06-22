package dev.pdsf.timewise.resource;

import dev.pdsf.timewise.model.PostScheduleDTO;
import dev.pdsf.timewise.model.domain.Task;
import dev.pdsf.timewise.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class SchedulerResource {
    private final ScheduleService scheduleService;

    public SchedulerResource(ScheduleService schedulerService) {
        this.scheduleService = schedulerService;
    }

    @PostMapping("/schedule")
    List<Task> create_schedule(@RequestBody @Valid PostScheduleDTO postScheduleDTO) {
        scheduleService.processSchedule(postScheduleDTO);
        return scheduleService.createSchedule(postScheduleDTO);
    }
}
