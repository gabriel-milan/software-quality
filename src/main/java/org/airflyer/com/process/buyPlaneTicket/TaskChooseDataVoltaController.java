package org.airflyer.com.process.buyPlaneTicket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buy-plane-ticket/task-choose-data-volta")
public class TaskChooseDataVoltaController {

    private final Logger log = LoggerFactory.getLogger(TaskChooseDataVoltaController.class);

    private final TaskChooseDataVoltaService taskChooseDataVoltaService;

    public TaskChooseDataVoltaController(TaskChooseDataVoltaService taskChooseDataVoltaService) {
        this.taskChooseDataVoltaService = taskChooseDataVoltaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskChooseDataVoltaContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseDataVoltaContextDTO taskChooseDataVoltaContext = taskChooseDataVoltaService.loadContext(id);
        return ResponseEntity.ok(taskChooseDataVoltaContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskChooseDataVoltaContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskChooseDataVoltaContextDTO taskChooseDataVoltaContext = taskChooseDataVoltaService.claim(id);
        return ResponseEntity.ok(taskChooseDataVoltaContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskChooseDataVoltaContextDTO taskChooseDataVoltaContext) {
        log.debug("REST request to complete BuyPlaneTicket.TaskChooseDataVolta {}", taskChooseDataVoltaContext.getTaskInstance().getId());
        taskChooseDataVoltaService.complete(taskChooseDataVoltaContext);
        return ResponseEntity.noContent().build();
    }
}
