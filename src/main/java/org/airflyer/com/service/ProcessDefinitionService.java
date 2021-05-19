package org.airflyer.com.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;
import org.airflyer.com.domain.ProcessDefinition;
import org.airflyer.com.domain.enumeration.StatusProcessDefinition;
import org.airflyer.com.repository.ProcessDefinitionRepository;
import org.airflyer.com.service.dto.ProcessDefinitionDTO;
import org.airflyer.com.service.dto.ProcessInstanceDTO;
import org.airflyer.com.service.mapper.ProcessDefinitionMapper;
import org.airflyer.com.web.rest.errors.BadRequestAlertException;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaExecutionListener;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaTaskListener;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProcessDefinition}.
 */
@Service
@Transactional
public class ProcessDefinitionService {

    private final Logger log = LoggerFactory.getLogger(ProcessDefinitionService.class);

    private final ProcessDefinitionRepository processDefinitionRepository;

    private final ProcessDefinitionMapper processDefinitionMapper;

    private final RepositoryService repositoryService;

    public ProcessDefinitionService(
        ProcessDefinitionRepository processDefinitionRepository,
        ProcessDefinitionMapper processDefinitionMapper,
        RepositoryService repositoryService
    ) {
        this.processDefinitionRepository = processDefinitionRepository;
        this.processDefinitionMapper = processDefinitionMapper;
        this.repositoryService = repositoryService;
    }

    /**
     * Save a processDefinition.
     *
     * @param processDefinitionDTO the entity to save.
     * @return the persisted entity.
     */
    public ProcessDefinitionDTO save(ProcessDefinitionDTO processDefinitionDTO) {
        log.debug("Request to save ProcessDefinition : {}", processDefinitionDTO);
        ProcessDefinition processDefinition = processDefinitionMapper.toEntity(processDefinitionDTO);
        BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(processDefinition.getSpecificationFile()));

        Process process = extracAndValidProcessFromModel(modelInstance);
        processDefinition.setBpmnProcessDefinitionId(process.getId());
        processDefinition.setName(process.getName());
        if (!process.getDocumentations().isEmpty()) {
            processDefinition.setDescription(process.getDocumentations().iterator().next().getRawTextContent());
        }

        configureListeners(modelInstance);

        org.camunda.bpm.engine.repository.Deployment camundaDeployment = repositoryService
            .createDeployment()
            .addModelInstance(processDefinition.getBpmnProcessDefinitionId() + ".bpmn", modelInstance)
            .name(processDefinition.getBpmnProcessDefinitionId())
            .deploy();

        org.camunda.bpm.engine.repository.ProcessDefinition camundaProcessDefinition = repositoryService
            .createProcessDefinitionQuery()
            .deploymentId(camundaDeployment.getId())
            .singleResult();

        processDefinition.setCamundaDeploymentId(camundaDeployment.getId());
        processDefinition.setCamundaProcessDefinitionId(camundaProcessDefinition.getId());
        processDefinition.setStatus(StatusProcessDefinition.ACTIVE);

        processDefinition = processDefinitionRepository.save(processDefinition);
        return processDefinitionMapper.toDto(processDefinition);
    }

    private Process extracAndValidProcessFromModel(BpmnModelInstance modelInstance) {
        ModelElementType processType = modelInstance.getModel().getType(Process.class);
        Process process = (Process) modelInstance.getModelElementsByType(processType).iterator().next();

        if (!process.isExecutable()) {
            throw new BadRequestAlertException(
                "BPMN Process is not executable",
                "BPMN Process is not executable",
                "BPMN Process is not executable"
            );
        }

        if (StringUtils.isBlank(process.getName())) {
            throw new BadRequestAlertException(
                "BPMN Process name not provided",
                "BPMN Process name not provided",
                "BPMN Process name not provided"
            );
        }

        return process;
    }

    private void configureListeners(BpmnModelInstance modelInstance) {
        ModelElementType processType = modelInstance.getModel().getType(Process.class);
        Process process = (Process) modelInstance.getModelElementsByType(processType).iterator().next();

        if (process.getExtensionElements() == null) {
            process.setExtensionElements(modelInstance.newInstance(ExtensionElements.class));
        }

        {
            CamundaExecutionListener processInstanceEndListener = process
                .getExtensionElements()
                .addExtensionElement(CamundaExecutionListener.class);
            processInstanceEndListener.setAttributeValue("event", "end");
            processInstanceEndListener.setAttributeValue("delegateExpression", "${camundaProcessInstanceEndListener}");
        }

        ModelElementType userTaskType = modelInstance.getModel().getType(UserTask.class);
        Collection<ModelElementInstance> userTaskInstances = modelInstance.getModelElementsByType(userTaskType);

        if (userTaskInstances == null) {
            return;
        }

        userTaskInstances
            .stream()
            .forEach(
                modelElementInstance -> {
                    UserTask userTask = (UserTask) modelElementInstance;

                    if (userTask.getExtensionElements() == null) {
                        userTask.setExtensionElements(modelInstance.newInstance(ExtensionElements.class));
                    }

                    {
                        CamundaTaskListener createListener = userTask.getExtensionElements().addExtensionElement(CamundaTaskListener.class);
                        createListener.setAttributeValue("event", "create");
                        createListener.setAttributeValue("delegateExpression", "${camundaTaskCreateListener}");
                    }

                    {
                        CamundaTaskListener assigmentListener = userTask
                            .getExtensionElements()
                            .addExtensionElement(CamundaTaskListener.class);
                        assigmentListener.setAttributeValue("event", "assignment");
                        assigmentListener.setAttributeValue("delegateExpression", "${camundaTaskAssignmentListener}");
                        userTask.getExtensionElements().getElements().add(assigmentListener);
                    }

                    {
                        CamundaTaskListener completeListener = userTask
                            .getExtensionElements()
                            .addExtensionElement(CamundaTaskListener.class);
                        completeListener.setAttributeValue("event", "complete");
                        completeListener.setAttributeValue("delegateExpression", "${camundaTaskCompleteListener}");
                        userTask.getExtensionElements().getElements().add(completeListener);
                    }

                    {
                        CamundaTaskListener deleteListener = userTask.getExtensionElements().addExtensionElement(CamundaTaskListener.class);
                        deleteListener.setAttributeValue("event", "delete");
                        deleteListener.setAttributeValue("delegateExpression", "${camundaTaskDeleteListener}");
                        userTask.getExtensionElements().getElements().add(deleteListener);
                    }

                    {
                        CamundaTaskListener updateListener = userTask.getExtensionElements().addExtensionElement(CamundaTaskListener.class);
                        updateListener.setAttributeValue("event", "update");
                        updateListener.setAttributeValue("delegateExpression", "${camundaTaskUpdateListener}");
                        userTask.getExtensionElements().getElements().add(updateListener);
                    }
                }
            );
    }

    /**
     * Get all the processDefinitions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProcessDefinitionDTO> findAll() {
        log.debug("Request to get all ProcessDefinitions");
        return processDefinitionRepository
            .findAll()
            .stream()
            .map(processDefinitionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one processDefinition by id.
     *
     * @param idOrBpmnProcessDefinitionId the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProcessDefinitionDTO> findOne(String idOrBpmnProcessDefinitionId) {
        log.debug("Request to get ProcessDefinition : {}", idOrBpmnProcessDefinitionId);
        if (StringUtils.isNumeric(idOrBpmnProcessDefinitionId)) {
            return processDefinitionRepository.findById(Long.parseLong(idOrBpmnProcessDefinitionId)).map(processDefinitionMapper::toDto);
        }
        return processDefinitionRepository.findByBpmnProcessDefinitionId(idOrBpmnProcessDefinitionId).map(processDefinitionMapper::toDto);
    }

    /**
     * Delete the processDefinition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProcessDefinition : {}", id);
        processDefinitionRepository.deleteById(id);
    }

    public static void main(String[] args) {
        try {
            File f = new File(
                "/Users/utelemaco/development/workspaceProcesshub/processhub-bpmnr-ri/src/main/resources/process-definitions/my-simple-process.bpmn"
            );
            FileInputStream fis = new FileInputStream(f);
            BpmnModelInstance modelInstance = Bpmn.readModelFromStream(fis);

            ProcessDefinitionService processDefinitionService = new ProcessDefinitionService(null, null, null);
            Process process = processDefinitionService.extracAndValidProcessFromModel(modelInstance);
            if (!process.getDocumentations().isEmpty()) {
                System.out.println(process.getDocumentations().iterator().next().getRawTextContent());
            }

            Iterator<Documentation> documentationIterator = process.getDocumentations().iterator();
            while (documentationIterator.hasNext()) {
                Documentation d = documentationIterator.next();
                System.out.println(d.getTextFormat());
                System.out.println(d.getRawTextContent());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
