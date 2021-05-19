package org.airflyer.com.process.buyPlaneTicket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buy-plane-ticket/task-make-payment")
public class TaskMakePaymentController {

    private final Logger log = LoggerFactory.getLogger(TaskMakePaymentController.class);

    private final TaskMakePaymentService taskMakePaymentService;

    public TaskMakePaymentController(TaskMakePaymentService taskMakePaymentService) {
        this.taskMakePaymentService = taskMakePaymentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskMakePaymentContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskMakePaymentContextDTO taskMakePaymentContext = taskMakePaymentService.loadContext(id);
        return ResponseEntity.ok(taskMakePaymentContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskMakePaymentContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskMakePaymentContextDTO taskMakePaymentContext = taskMakePaymentService.claim(id);
        return ResponseEntity.ok(taskMakePaymentContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskMakePaymentContextDTO taskMakePaymentContext) {
        log.debug("REST request to complete BuyPlaneTicket.TaskMakePayment {}", taskMakePaymentContext.getTaskInstance().getId());
        taskMakePaymentService.complete(taskMakePaymentContext);
        return ResponseEntity.noContent().build();
    }
}
