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
public class TaskMakePaymentService {

    private final TaskInstanceService taskInstanceService;

    private final BuyTicketService buyTicketService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final BuyPlaneTicketRepository buyPlaneTicketRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskMakePaymentMapper taskMakePaymentMapper;

    public TaskMakePaymentService(
        TaskInstanceService taskInstanceService,
        BuyTicketService buyTicketService,
        TaskInstanceRepository taskInstanceRepository,
        BuyPlaneTicketRepository buyPlaneTicketRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskMakePaymentMapper taskMakePaymentMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.buyTicketService = buyTicketService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.buyPlaneTicketRepository = buyPlaneTicketRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskMakePaymentMapper = taskMakePaymentMapper;
    }

    public TaskMakePaymentContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        BuyPlaneTicketDTO buyPlaneTicket = buyPlaneTicketRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskMakePaymentMapper::toBuyPlaneTicketDTO)
            .orElseThrow();

        TaskMakePaymentContextDTO taskMakePaymentContext = new TaskMakePaymentContextDTO();
        taskMakePaymentContext.setTaskInstance(taskInstanceDTO);
        taskMakePaymentContext.setBuyPlaneTicket(buyPlaneTicket);

        return taskMakePaymentContext;
    }

    public TaskMakePaymentContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskMakePaymentContextDTO taskMakePaymentContext) {
        BuyTicketDTO buyTicketDTO = buyTicketService
            .findOne(taskMakePaymentContext.getBuyPlaneTicket().getBuyTicket().getId())
            .orElseThrow();
        buyTicketDTO.setName(taskMakePaymentContext.getBuyPlaneTicket().getBuyTicket().getName());
        buyTicketDTO.setCartaoCredito(taskMakePaymentContext.getBuyPlaneTicket().getBuyTicket().getCartaoCredito());
        buyTicketService.save(buyTicketDTO);
    }

    public void complete(TaskMakePaymentContextDTO taskMakePaymentContext) {
        save(taskMakePaymentContext);
        taskInstanceService.complete(taskMakePaymentContext.getTaskInstance(), taskMakePaymentContext.getBuyPlaneTicket());
    }
}
