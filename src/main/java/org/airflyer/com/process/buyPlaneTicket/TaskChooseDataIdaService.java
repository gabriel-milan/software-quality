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
public class TaskChooseDataIdaService {

    private final TaskInstanceService taskInstanceService;

    private final BuyTicketService buyTicketService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final BuyPlaneTicketRepository buyPlaneTicketRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskChooseDataIdaMapper taskChooseDataIdaMapper;

    public TaskChooseDataIdaService(
        TaskInstanceService taskInstanceService,
        BuyTicketService buyTicketService,
        TaskInstanceRepository taskInstanceRepository,
        BuyPlaneTicketRepository buyPlaneTicketRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskChooseDataIdaMapper taskChooseDataIdaMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.buyTicketService = buyTicketService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.buyPlaneTicketRepository = buyPlaneTicketRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskChooseDataIdaMapper = taskChooseDataIdaMapper;
    }

    public TaskChooseDataIdaContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        BuyPlaneTicketDTO buyPlaneTicket = buyPlaneTicketRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskChooseDataIdaMapper::toBuyPlaneTicketDTO)
            .orElseThrow();

        TaskChooseDataIdaContextDTO taskChooseDataIdaContext = new TaskChooseDataIdaContextDTO();
        taskChooseDataIdaContext.setTaskInstance(taskInstanceDTO);
        taskChooseDataIdaContext.setBuyPlaneTicket(buyPlaneTicket);

        return taskChooseDataIdaContext;
    }

    public TaskChooseDataIdaContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskChooseDataIdaContextDTO taskChooseDataIdaContext) {
        BuyTicketDTO buyTicketDTO = buyTicketService
            .findOne(taskChooseDataIdaContext.getBuyPlaneTicket().getBuyTicket().getId())
            .orElseThrow();
        buyTicketDTO.setName(taskChooseDataIdaContext.getBuyPlaneTicket().getBuyTicket().getName());
        buyTicketDTO.setDataIda(taskChooseDataIdaContext.getBuyPlaneTicket().getBuyTicket().getDataIda());
        buyTicketService.save(buyTicketDTO);
    }

    public void complete(TaskChooseDataIdaContextDTO taskChooseDataIdaContext) {
        save(taskChooseDataIdaContext);
        taskInstanceService.complete(taskChooseDataIdaContext.getTaskInstance(), taskChooseDataIdaContext.getBuyPlaneTicket());
    }
}
