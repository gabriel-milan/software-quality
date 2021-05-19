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
public class TaskChooseAirlineService {

    private final TaskInstanceService taskInstanceService;

    private final BuyTicketService buyTicketService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final BuyPlaneTicketRepository buyPlaneTicketRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskChooseAirlineMapper taskChooseAirlineMapper;

    public TaskChooseAirlineService(
        TaskInstanceService taskInstanceService,
        BuyTicketService buyTicketService,
        TaskInstanceRepository taskInstanceRepository,
        BuyPlaneTicketRepository buyPlaneTicketRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskChooseAirlineMapper taskChooseAirlineMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.buyTicketService = buyTicketService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.buyPlaneTicketRepository = buyPlaneTicketRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskChooseAirlineMapper = taskChooseAirlineMapper;
    }

    public TaskChooseAirlineContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        BuyPlaneTicketDTO buyPlaneTicket = buyPlaneTicketRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskChooseAirlineMapper::toBuyPlaneTicketDTO)
            .orElseThrow();

        TaskChooseAirlineContextDTO taskChooseAirlineContext = new TaskChooseAirlineContextDTO();
        taskChooseAirlineContext.setTaskInstance(taskInstanceDTO);
        taskChooseAirlineContext.setBuyPlaneTicket(buyPlaneTicket);

        return taskChooseAirlineContext;
    }

    public TaskChooseAirlineContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskChooseAirlineContextDTO taskChooseAirlineContext) {
        BuyTicketDTO buyTicketDTO = buyTicketService
            .findOne(taskChooseAirlineContext.getBuyPlaneTicket().getBuyTicket().getId())
            .orElseThrow();
        buyTicketDTO.setName(taskChooseAirlineContext.getBuyPlaneTicket().getBuyTicket().getName());
        buyTicketDTO.setCompanhiaAerea(taskChooseAirlineContext.getBuyPlaneTicket().getBuyTicket().getCompanhiaAerea());
        buyTicketService.save(buyTicketDTO);
    }

    public void complete(TaskChooseAirlineContextDTO taskChooseAirlineContext) {
        save(taskChooseAirlineContext);
        taskInstanceService.complete(taskChooseAirlineContext.getTaskInstance(), taskChooseAirlineContext.getBuyPlaneTicket());
    }
}
