package dev.pdsf.timewise.resource;

import dev.pdsf.timewise.model.PostScheduleDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class SchedulerResource {
    //private final SchedulerService schedulerService;

    //public SchedulerResource(SchedulerService schedulerService) {
    //    this.schedulerService = schedulerService;
    //}

    @PostMapping("/schedule")
    PostScheduleDTO create_schedule(@RequestBody @Valid PostScheduleDTO postScheduleDTO) {
        return postScheduleDTO;
    }
}
