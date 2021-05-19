package org.airflyer.com.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.airflyer.com.service.ProcessDefinitionService;
import org.airflyer.com.service.ProcessInstanceService;
import org.airflyer.com.service.TaskInstanceService;
import org.airflyer.com.service.dto.ProcessDefinitionDTO;
import org.airflyer.com.service.dto.ProcessInstanceDTO;
import org.airflyer.com.service.dto.TaskInstanceDTO;
import org.airflyer.com.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.airflyer.com.domain.ProcessDefinition}.
 */
@RestController
@RequestMapping("/api")
public class ProcessDefinitionResource {

    private final Logger log = LoggerFactory.getLogger(ProcessDefinitionResource.class);

    private static final String ENTITY_NAME = "processDefinition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProcessDefinitionService processDefinitionService;

    private final ProcessInstanceService processInstanceService;

    private final TaskInstanceService taskInstanceService;

    public ProcessDefinitionResource(
        ProcessDefinitionService processDefinitionService,
        ProcessInstanceService processInstanceService,
        TaskInstanceService taskInstanceService
    ) {
        this.processDefinitionService = processDefinitionService;
        this.processInstanceService = processInstanceService;
        this.taskInstanceService = taskInstanceService;
    }

    /**
     * {@code POST  /process-definitions} : Create a new processDefinition.
     *
     * @param processDefinitionDTO the processDefinitionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new processDefinitionDTO, or with status {@code 400 (Bad Request)} if the processDefinition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/process-definitions")
    public ResponseEntity<ProcessDefinitionDTO> createProcessDefinition(@RequestBody ProcessDefinitionDTO processDefinitionDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProcessDefinition : {}", processDefinitionDTO);
        if (processDefinitionDTO.getId() != null) {
            throw new BadRequestAlertException("A new processDefinition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProcessDefinitionDTO result = processDefinitionService.save(processDefinitionDTO);
        return ResponseEntity
            .created(new URI("/api/process-definitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /process-definitions} : Updates an existing processDefinition.
     *
     * @param processDefinitionDTO the processDefinitionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated processDefinitionDTO,
     * or with status {@code 400 (Bad Request)} if the processDefinitionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the processDefinitionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/process-definitions")
    public ResponseEntity<ProcessDefinitionDTO> updateProcessDefinition(@RequestBody ProcessDefinitionDTO processDefinitionDTO)
        throws URISyntaxException {
        log.debug("REST request to update ProcessDefinition : {}", processDefinitionDTO);
        if (processDefinitionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProcessDefinitionDTO result = processDefinitionService.save(processDefinitionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, processDefinitionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /process-definitions} : get all the processDefinitions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of processDefinitions in body.
     */
    @GetMapping("/process-definitions")
    public List<ProcessDefinitionDTO> getAllProcessDefinitions() {
        log.debug("REST request to get all ProcessDefinitions");
        return processDefinitionService.findAll();
    }

    /**
     * {@code GET  /process-definitions/:id} : get the "id" processDefinition.
     *
     * @param idOrBpmnProcessDefinitionId the id of the processDefinitionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the processDefinitionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/process-definitions/{idOrBpmnProcessDefinitionId}")
    public ResponseEntity<ProcessDefinitionDTO> getProcessDefinition(@PathVariable String idOrBpmnProcessDefinitionId) {
        log.debug("REST request to get ProcessDefinition : {}", idOrBpmnProcessDefinitionId);
        Optional<ProcessDefinitionDTO> processDefinitionDTO = processDefinitionService.findOne(idOrBpmnProcessDefinitionId);
        return ResponseUtil.wrapOrNotFound(processDefinitionDTO);
    }

    /**
     * {@code GET  /process-definitions/:idOrBpmnProcessDefinitionId/instances} : get the "idOrBpmnProcessDefinitionId" processDefinition.
     *
     * @param idOrBpmnProcessDefinitionId the id of the processDefinitionDTO owner of the ProcessInstances.
     * @return the list of processInstanceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/process-definitions/{idOrBpmnProcessDefinitionId}/instances")
    public List<ProcessInstanceDTO> getProcessInstances(@PathVariable String idOrBpmnProcessDefinitionId) {
        log.debug("REST request to get ProcessInstances of the ProcessDefinition : {}", idOrBpmnProcessDefinitionId);
        return processInstanceService.findByProcessDefinition(idOrBpmnProcessDefinitionId);
    }

    /**
     * {@code GET  /process-definition/:idOrBpmnProcessDefinitionId/tasks} : get the "id" processInstance.
     *
     * @param idOrBpmnProcessDefinitionId the id or bpmnProcessDefinitionId of the processDefinition owner of the TaskInstances.
     * @return the list of processInstanceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/process-definition/{idOrBpmnProcessDefinitionId}/tasks")
    public List<TaskInstanceDTO> getTaskInstances(@PathVariable String idOrBpmnProcessDefinitionId) {
        log.debug("REST request to get TaskInstances of the ProcessDefinition : {}", idOrBpmnProcessDefinitionId);
        return taskInstanceService.findByProcessDefinition(idOrBpmnProcessDefinitionId);
    }

    /**
     * {@code DELETE  /process-definitions/:id} : delete the "id" processDefinition.
     *
     * @param id the id of the processDefinitionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/process-definitions/{id}")
    public ResponseEntity<Void> deleteProcessDefinition(@PathVariable Long id) {
        log.debug("REST request to delete ProcessDefinition : {}", id);
        processDefinitionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
