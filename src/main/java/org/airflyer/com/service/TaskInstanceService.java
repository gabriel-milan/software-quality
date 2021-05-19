package org.airflyer.com.service;

import java.util.*;
import java.util.stream.Collectors;
import liquibase.pro.packaged.T;
import org.airflyer.com.camunda.CamundaConstants;
import org.airflyer.com.domain.TaskInstance;
import org.airflyer.com.domain.enumeration.StatusTaskInstance;
import org.airflyer.com.repository.TaskInstanceRepository;
import org.airflyer.com.security.SecurityUtils;
import org.airflyer.com.service.dto.ProcessInstanceDTO;
import org.airflyer.com.service.dto.TaskInstanceDTO;
import org.airflyer.com.service.mapper.TaskInstanceMapper;
import org.camunda.bpm.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaskInstance}.
 */
@Service
@Transactional
public class TaskInstanceService {

    private final Logger log = LoggerFactory.getLogger(TaskInstanceService.class);

    private final TaskInstanceRepository taskInstanceRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskService taskService;

    private final ProcessDefinitionService processDefinitionService;

    public TaskInstanceService(
        TaskInstanceRepository taskInstanceRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskService taskService,
        ProcessDefinitionService processDefinitionService
    ) {
        this.taskInstanceRepository = taskInstanceRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskService = taskService;
        this.processDefinitionService = processDefinitionService;
    }

    /**
     * Save a taskInstance.
     *
     * @param taskInstanceDTO the entity to save.
     * @return the persisted entity.
     */
    public TaskInstanceDTO save(TaskInstanceDTO taskInstanceDTO) {
        log.debug("Request to save TaskInstance : {}", taskInstanceDTO);
        TaskInstance taskInstance = taskInstanceMapper.toEntity(taskInstanceDTO);
        taskInstance = taskInstanceRepository.save(taskInstance);
        return taskInstanceMapper.toDto(taskInstance);
    }

    /**
     * Get all the taskInstances.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TaskInstanceDTO> findAll() {
        log.debug("Request to get all TaskInstances");
        return taskInstanceRepository.findAll().stream().map(taskInstanceMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one taskInstance by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TaskInstanceDTO> findOne(Long id) {
        log.debug("Request to get TaskInstance : {}", id);
        return taskInstanceRepository.findById(id).map(taskInstanceMapper::toDto);
    }

    public Optional<TaskInstanceDTO> claim(Long id) {
        log.debug("Request to claim TaskInstance : {}", id);
        Optional<TaskInstance> optionalTaskInstance = taskInstanceRepository.findById(id);
        if (optionalTaskInstance.isPresent()) {
            TaskInstance taskInstance = optionalTaskInstance.get();
            taskInstance.setStatus(StatusTaskInstance.ASSIGNED);
            taskInstance.setAssignee(SecurityUtils.getCurrentUserLogin().get());
            taskService.claim(taskInstance.getTaskId(), SecurityUtils.getCurrentUserLogin().get());
            taskInstanceRepository.save(taskInstance);
        }
        return optionalTaskInstance.map(taskInstanceMapper::toDto);
    }

    public void complete(TaskInstanceDTO taskInstanceDTO) {
        ProcessInstanceDTO processInstanceDTO = taskInstanceDTO.getProcessInstance();
        //TODO... updating with data from request...
        //processInstanceService.save(processInstance);
        complete(taskInstanceDTO, processInstanceDTO);
    }

    public void complete(TaskInstanceDTO taskInstance, Object processInstance) {
        log.debug("Concluding taskIntanceId: {}, camundaTaskId: {}", taskInstance.getId(), taskInstance.getTaskId());
        Map<String, Object> params = new HashMap<>();
        params.put(CamundaConstants.PROCESS_INSTANCE, processInstance);
        params.put(CamundaConstants.PROCESS_INSTANCE_INITIALS, processInstance);
        taskService.claim(taskInstance.getTaskId(), SecurityUtils.getCurrentUserLogin().get());
        taskService.complete(taskInstance.getTaskId(), params);
    }

    public List<TaskInstanceDTO> findByProcessDefinition(String idOrBpmnProcessDefinitionId) {
        log.debug("Request to get TaskInstances of the ProcessDefinition : {}", idOrBpmnProcessDefinitionId);
        return taskInstanceRepository
            .findByProcessDefinitionIdOrBpmnProcessDefinitionId(idOrBpmnProcessDefinitionId)
            .stream()
            .map(taskInstanceMapper::toDto)
            .collect(Collectors.toList());
    }

    public List<TaskInstanceDTO> findByProcessInstance(Long id) {
        log.debug("Request to get TaskInstances of the ProcessInstance : {}", id);
        return taskInstanceRepository.findByProcessInstanceId(id).stream().map(taskInstanceMapper::toDto).collect(Collectors.toList());
    }
}
