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
public class TaskChooseOriginDestinationService {

    private final TaskInstanceService taskInstanceService;

    private final BuyTicketService buyTicketService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final BuyPlaneTicketRepository buyPlaneTicketRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskChooseOriginDestinationMapper taskChooseOriginDestinationMapper;

    public TaskChooseOriginDestinationService(
        TaskInstanceService taskInstanceService,
        BuyTicketService buyTicketService,
        TaskInstanceRepository taskInstanceRepository,
        BuyPlaneTicketRepository buyPlaneTicketRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskChooseOriginDestinationMapper taskChooseOriginDestinationMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.buyTicketService = buyTicketService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.buyPlaneTicketRepository = buyPlaneTicketRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskChooseOriginDestinationMapper = taskChooseOriginDestinationMapper;
    }

    public TaskChooseOriginDestinationContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        BuyPlaneTicketDTO buyPlaneTicket = buyPlaneTicketRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskChooseOriginDestinationMapper::toBuyPlaneTicketDTO)
            .orElseThrow();

        TaskChooseOriginDestinationContextDTO taskChooseOriginDestinationContext = new TaskChooseOriginDestinationContextDTO();
        taskChooseOriginDestinationContext.setTaskInstance(taskInstanceDTO);
        taskChooseOriginDestinationContext.setBuyPlaneTicket(buyPlaneTicket);

        return taskChooseOriginDestinationContext;
    }

    public TaskChooseOriginDestinationContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskChooseOriginDestinationContextDTO taskChooseOriginDestinationContext) {
        BuyTicketDTO buyTicketDTO = buyTicketService
            .findOne(taskChooseOriginDestinationContext.getBuyPlaneTicket().getBuyTicket().getId())
            .orElseThrow();
        buyTicketDTO.setName(taskChooseOriginDestinationContext.getBuyPlaneTicket().getBuyTicket().getName());
        buyTicketDTO.setOrigem(taskChooseOriginDestinationContext.getBuyPlaneTicket().getBuyTicket().getOrigem());
        buyTicketDTO.setDestino(taskChooseOriginDestinationContext.getBuyPlaneTicket().getBuyTicket().getDestino());
        buyTicketService.save(buyTicketDTO);
    }

    public void complete(TaskChooseOriginDestinationContextDTO taskChooseOriginDestinationContext) {
        save(taskChooseOriginDestinationContext);
        taskInstanceService.complete(
            taskChooseOriginDestinationContext.getTaskInstance(),
            taskChooseOriginDestinationContext.getBuyPlaneTicket()
        );
    }
}
