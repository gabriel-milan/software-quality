package org.airflyer.com.process.buyPlaneTicket;

import org.airflyer.com.repository.BuyPlaneTicketRepository;
import org.airflyer.com.repository.TaskInstanceRepository;
import org.airflyer.com.service.BuyTicketService;
import org.airflyer.com.service.TaskInstanceService;
import org.airflyer.com.service.dto.BuyPlaneTicketDTO;
import org.airflyer.com.service.dto.BuyTicketDTO;
import org.airflyer.com.service.dto.TaskInstanceDTO;
import org.airflyer.com.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskChooseDataVoltaService {

    private final TaskInstanceService taskInstanceService;

    private final BuyTicketService buyTicketService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final BuyPlaneTicketRepository buyPlaneTicketRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskChooseDataVoltaMapper taskChooseDataVoltaMapper;

    public TaskChooseDataVoltaService(
        TaskInstanceService taskInstanceService,
        BuyTicketService buyTicketService,
        TaskInstanceRepository taskInstanceRepository,
        BuyPlaneTicketRepository buyPlaneTicketRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskChooseDataVoltaMapper taskChooseDataVoltaMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.buyTicketService = buyTicketService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.buyPlaneTicketRepository = buyPlaneTicketRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskChooseDataVoltaMapper = taskChooseDataVoltaMapper;
    }

    public TaskChooseDataVoltaContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        BuyPlaneTicketDTO buyPlaneTicket = buyPlaneTicketRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskChooseDataVoltaMapper::toBuyPlaneTicketDTO)
            .orElseThrow();

        TaskChooseDataVoltaContextDTO taskChooseDataVoltaContext = new TaskChooseDataVoltaContextDTO();
        taskChooseDataVoltaContext.setTaskInstance(taskInstanceDTO);
        taskChooseDataVoltaContext.setBuyPlaneTicket(buyPlaneTicket);

        return taskChooseDataVoltaContext;
    }

    public TaskChooseDataVoltaContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskChooseDataVoltaContextDTO taskChooseDataVoltaContext) {
        BuyTicketDTO buyTicketDTO = buyTicketService
            .findOne(taskChooseDataVoltaContext.getBuyPlaneTicket().getBuyTicket().getId())
            .orElseThrow();
        buyTicketDTO.setName(taskChooseDataVoltaContext.getBuyPlaneTicket().getBuyTicket().getName());
        buyTicketDTO.setDataVolta(taskChooseDataVoltaContext.getBuyPlaneTicket().getBuyTicket().getDataVolta());
        buyTicketService.save(buyTicketDTO);
    }

    public void complete(TaskChooseDataVoltaContextDTO taskChooseDataVoltaContext) {
        save(taskChooseDataVoltaContext);
        taskInstanceService.complete(taskChooseDataVoltaContext.getTaskInstance(), taskChooseDataVoltaContext.getBuyPlaneTicket());
    }
}
