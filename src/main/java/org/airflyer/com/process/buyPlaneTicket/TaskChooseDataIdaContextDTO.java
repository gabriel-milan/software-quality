package org.airflyer.com.process.buyPlaneTicket;

import org.airflyer.com.service.dto.BuyPlaneTicketDTO;
import org.airflyer.com.service.dto.TaskInstanceDTO;

public class TaskChooseDataIdaContextDTO {

    private BuyPlaneTicketDTO buyPlaneTicket;
    private TaskInstanceDTO taskInstance;

    public BuyPlaneTicketDTO getBuyPlaneTicket() {
        return buyPlaneTicket;
    }

    public void setBuyPlaneTicket(BuyPlaneTicketDTO buyPlaneTicket) {
        this.buyPlaneTicket = buyPlaneTicket;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
