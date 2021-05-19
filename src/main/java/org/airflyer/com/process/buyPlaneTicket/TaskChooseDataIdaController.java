package org.airflyer.com.process.buyPlaneTicket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buy-plane-ticket/task-choose-data-ida")
public class TaskChooseDataIdaController {

    private final Logger log = LoggerFactory.getLogger(TaskChooseDataIdaController.class);

    private final TaskChooseDataIdaService taskChooseDataIdaService;

    public TaskChooseDataIdaController(TaskChooseDataIdaService taskChooseDataIdaService) {
        this.taskChooseDataIdaService = taskChooseDataIdaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskChooseDataIdaContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseDataIdaContextDTO taskChooseDataIdaContext = taskChooseDataIdaService.loadContext(id);
        return ResponseEntity.ok(taskChooseDataIdaContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskChooseDataIdaContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseDataIdaContextDTO taskChooseDataIdaContext = taskChooseDataIdaService.claim(id);
        return ResponseEntity.ok(taskChooseDataIdaContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskChooseDataIdaContextDTO taskChooseDataIdaContext) {
        log.debug("REST request to complete BuyPlaneTicket.TaskChooseDataIda {}", taskChooseDataIdaContext.getTaskInstance().getId());
        taskChooseDataIdaService.complete(taskChooseDataIdaContext);
        return ResponseEntity.noContent().build();
    }
}
