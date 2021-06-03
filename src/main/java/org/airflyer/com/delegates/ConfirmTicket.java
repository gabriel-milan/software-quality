package org.airflyer.com.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.airflyer.com.service.dto.BuyPlaneTicketDTO;
import org.airflyer.com.domain.BuyTicket;

import java.math.BigDecimal;

@Component
public class ConfirmTicket implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        BuyPlaneTicketDTO pi = (BuyPlaneTicketDTO) delegateExecution.getVariable("pi");

        String name = pi.getBuyTicket().getName();

        System.out.println();
        System.out.println("=============================================");
        System.out.println("RESERVA CONFIRMADA");
        System.out.println(name);
        System.out.println("OBRIGADO POR ESCOLHER A AIR FLYER");
        System.out.println("=============================================");
        System.out.println();
    }

}