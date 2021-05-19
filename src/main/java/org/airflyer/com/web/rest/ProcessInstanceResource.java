package org.airflyer.com.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.airflyer.com.service.ProcessInstanceService;
import org.airflyer.com.service.TaskInstanceService;
import org.airflyer.com.service.dto.ProcessInstanceDTO;
import org.airflyer.com.service.dto.TaskInstanceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.airflyer.com.domain.ProcessInstance}.
 */
@RestController
@RequestMapping("/api")
public class ProcessInstanceResource {

    private final Logger log = LoggerFactory.getLogger(ProcessInstanceResource.class);

    private static final String ENTITY_NAME = "processInstance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProcessInstanceService processInstanceService;

    private final TaskInstanceService taskInstanceService;

    public ProcessInstanceResource(ProcessInstanceService processInstanceService, TaskInstanceService taskInstanceService) {
        this.processInstanceService = processInstanceService;
        this.taskInstanceService = taskInstanceService;
    }

    /**
     * {@code POST  /process-definitions} : Create a new processDefinition.
     *
     * @param processInstanceDTO the processInstanceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new processInstanceDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/process-instances")
    public ResponseEntity<ProcessInstanceDTO> create(@RequestBody ProcessInstanceDTO processInstanceDTO) throws URISyntaxException {
        log.debug("REST request to init a ProcessDefinition : {}", processInstanceDTO.getProcessDefinition());
        ProcessInstanceDTO result = processInstanceService.create(processInstanceDTO);
        return ResponseEntity
            .created(new URI("/api/process-definitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /process-instances} : get all the processInstances.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of processInstances in body.
     */
    @GetMapping("/process-instances")
    public List<ProcessInstanceDTO> getAllProcessInstances() {
        log.debug("REST request to get all ProcessInstances");
        return processInstanceService.findAll();
    }

    /**
     * {@code GET  /process-instances/:id/tasks} : get the "id" processInstance.
     *
     * @param id the id of the processInstance owner of the TaskInstances.
     * @return the list of processInstanceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/process-instances/{id}/tasks")
    public List<TaskInstanceDTO> getTaskInstances(@PathVariable Long id) {
        log.debug("REST request to get TaskInstances of ProcessInstance : {}", id);
        return taskInstanceService.findByProcessInstance(id);
    }

    /**
     * {@code GET  /process-instances/:id} : get the "id" processInstance.
     *
     * @param id the id of the processInstanceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the processInstanceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/process-instances/{id}")
    public ResponseEntity<ProcessInstanceDTO> getProcessInstance(@PathVariable Long id) {
        log.debug("REST request to get ProcessInstance : {}", id);
        Optional<ProcessInstanceDTO> processInstanceDTO = processInstanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(processInstanceDTO);
    }
}
