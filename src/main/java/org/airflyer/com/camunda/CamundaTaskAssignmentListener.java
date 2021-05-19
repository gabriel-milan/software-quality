package org.airflyer.com.camunda;

import java.time.Instant;
import java.util.Optional;
import org.airflyer.com.domain.TaskInstance;
import org.airflyer.com.domain.enumeration.StatusTaskInstance;
import org.airflyer.com.repository.TaskInstanceRepository;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class CamundaTaskAssignmentListener implements TaskListener {

    private final TaskInstanceRepository taskInstanceRepository;

    public CamundaTaskAssignmentListener(TaskInstanceRepository taskInstanceRepository) {
        this.taskInstanceRepository = taskInstanceRepository;
    }

    public void notify(DelegateTask delegateTask) {
        Optional<TaskInstance> optionalTaskInstance = taskInstanceRepository.findByTaskId(delegateTask.getId());

        if (optionalTaskInstance.isPresent()) {
            TaskInstance taskInstance = optionalTaskInstance.get();
            taskInstance.setStatus(StatusTaskInstance.ASSIGNED);
            taskInstance.setAssignee(delegateTask.getAssignee());
            taskInstanceRepository.save(taskInstance);
        }
    }
}
