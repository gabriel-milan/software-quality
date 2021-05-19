package org.airflyer.com.process.buyPlaneTicket;

import org.airflyer.com.domain.BuyPlaneTicket;
import org.airflyer.com.domain.BuyTicket;
import org.airflyer.com.service.dto.BuyPlaneTicketDTO;
import org.airflyer.com.service.dto.BuyTicketDTO;
import org.airflyer.com.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskChooseDataVoltaMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    BuyPlaneTicketDTO toBuyPlaneTicketDTO(BuyPlaneTicket buyPlaneTicket);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "dataVolta", source = "dataVolta")
    BuyTicketDTO toBuyTicketDTO(BuyTicket buyTicket);
}
