package org.airflyer.com.web.rest;

import java.util.List;
import java.util.Optional;
import org.airflyer.com.service.TaskInstanceService;
import org.airflyer.com.service.dto.TaskInstanceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.airflyer.com.domain.TaskInstance}.
 */
@RestController
@RequestMapping("/api")
public class TaskInstanceResource {

    private final Logger log = LoggerFactory.getLogger(TaskInstanceResource.class);

    private final TaskInstanceService taskInstanceService;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public TaskInstanceResource(TaskInstanceService taskInstanceService) {
        this.taskInstanceService = taskInstanceService;
    }

    /**
     * {@code GET  /task-instances} : get all the taskInstances.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taskInstances in body.
     */
    @GetMapping("/task-instances")
    public List<TaskInstanceDTO> getAllTaskInstances() {
        log.debug("REST request to get all TaskInstances");
        return taskInstanceService.findAll();
    }

    /**
     * {@code GET  /task-instances/:id} : get the "id" taskInstance.
     *
     * @param id the id of the taskInstanceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskInstanceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/task-instances/{id}")
    public ResponseEntity<TaskInstanceDTO> getTaskInstance(@PathVariable Long id) {
        log.debug("REST request to get TaskInstance : {}", id);
        Optional<TaskInstanceDTO> taskInstanceDTO = taskInstanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskInstanceDTO);
    }

    @GetMapping("/task-instances/{id}/claim")
    public ResponseEntity<TaskInstanceDTO> claimTaskInstance(@PathVariable Long id) {
        log.debug("REST request to get TaskInstance : {}", id);
        Optional<TaskInstanceDTO> taskInstanceDTO = taskInstanceService.claim(id);
        return ResponseUtil.wrapOrNotFound(taskInstanceDTO);
    }

    @PostMapping("/task-instances/complete")
    public ResponseEntity<Void> completeTaskInstance(@RequestBody TaskInstanceDTO taskInstanceDTO) {
        log.debug("REST request to complete TaskInstance: Id {}, TaskId {}", taskInstanceDTO.getId(), taskInstanceDTO.getTaskId());
        taskInstanceService.complete(taskInstanceDTO);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createAlert(applicationName, "taskInstance.executed", taskInstanceDTO.toString()))
            .build();
    }
}
