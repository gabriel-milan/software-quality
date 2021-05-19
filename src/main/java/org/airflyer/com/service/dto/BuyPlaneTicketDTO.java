package org.airflyer.com.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.airflyer.com.domain.BuyPlaneTicket} entity.
 */
public class BuyPlaneTicketDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private BuyTicketDTO buyTicket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public BuyTicketDTO getBuyTicket() {
        return buyTicket;
    }

    public void setBuyTicket(BuyTicketDTO buyTicket) {
        this.buyTicket = buyTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BuyPlaneTicketDTO)) {
            return false;
        }

        BuyPlaneTicketDTO buyPlaneTicketDTO = (BuyPlaneTicketDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, buyPlaneTicketDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BuyPlaneTicketDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", buyTicket=" + getBuyTicket() +
            "}";
    }
}
