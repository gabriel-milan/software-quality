package org.airflyer.com.service.mapper;

import org.airflyer.com.domain.*;
import org.airflyer.com.service.dto.BuyPlaneTicketDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BuyPlaneTicket} and its DTO {@link BuyPlaneTicketDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, BuyTicketMapper.class })
public interface BuyPlaneTicketMapper extends EntityMapper<BuyPlaneTicketDTO, BuyPlaneTicket> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "buyTicket", source = "buyTicket")
    BuyPlaneTicketDTO toDto(BuyPlaneTicket s);
}
