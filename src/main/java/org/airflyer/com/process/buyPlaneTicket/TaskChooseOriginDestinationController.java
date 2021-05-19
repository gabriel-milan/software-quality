package org.airflyer.com.process.buyPlaneTicket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buy-plane-ticket/task-choose-origin-destination")
public class TaskChooseOriginDestinationController {

    private final Logger log = LoggerFactory.getLogger(TaskChooseOriginDestinationController.class);

    private final TaskChooseOriginDestinationService taskChooseOriginDestinationService;

    public TaskChooseOriginDestinationController(TaskChooseOriginDestinationService taskChooseOriginDestinationService) {
        this.taskChooseOriginDestinationService = taskChooseOriginDestinationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskChooseOriginDestinationContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseOriginDestinationContextDTO taskChooseOriginDestinationContext = taskChooseOriginDestinationService.loadContext(id);
        return ResponseEntity.ok(taskChooseOriginDestinationContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskChooseOriginDestinationContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseOriginDestinationContextDTO taskChooseOriginDestinationContext = taskChooseOriginDestinationService.claim(id);
        return ResponseEntity.ok(taskChooseOriginDestinationContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskChooseOriginDestinationContextDTO taskChooseOriginDestinationContext) {
        log.debug(
            "REST request to complete BuyPlaneTicket.TaskChooseOriginDestination {}",
            taskChooseOriginDestinationContext.getTaskInstance().getId()
        );
        taskChooseOriginDestinationService.complete(taskChooseOriginDestinationContext);
        return ResponseEntity.noContent().build();
    }
}
