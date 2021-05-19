package org.airflyer.com.process.buyPlaneTicket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buy-plane-ticket/task-choose-airline")
public class TaskChooseAirlineController {

    private final Logger log = LoggerFactory.getLogger(TaskChooseAirlineController.class);

    private final TaskChooseAirlineService taskChooseAirlineService;

    public TaskChooseAirlineController(TaskChooseAirlineService taskChooseAirlineService) {
        this.taskChooseAirlineService = taskChooseAirlineService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskChooseAirlineContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseAirlineContextDTO taskChooseAirlineContext = taskChooseAirlineService.loadContext(id);
        return ResponseEntity.ok(taskChooseAirlineContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskChooseAirlineContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseAirlineContextDTO taskChooseAirlineContext = taskChooseAirlineService.claim(id);
        return ResponseEntity.ok(taskChooseAirlineContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskChooseAirlineContextDTO taskChooseAirlineContext) {
        log.debug("REST request to complete BuyPlaneTicket.TaskChooseAirline {}", taskChooseAirlineContext.getTaskInstance().getId());
        taskChooseAirlineService.complete(taskChooseAirlineContext);
        return ResponseEntity.noContent().build();
    }
}
