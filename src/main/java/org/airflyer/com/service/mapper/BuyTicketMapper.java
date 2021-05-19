package org.airflyer.com.service.mapper;

import org.airflyer.com.domain.*;
import org.airflyer.com.service.dto.BuyTicketDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BuyTicket} and its DTO {@link BuyTicketDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BuyTicketMapper extends EntityMapper<BuyTicketDTO, BuyTicket> {}
