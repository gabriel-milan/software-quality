package org.airflyer.com.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.airflyer.com.domain.BuyPlaneTicket;
import org.airflyer.com.domain.ProcessInstance;
import org.airflyer.com.repository.BuyPlaneTicketRepository;
import org.airflyer.com.repository.BuyTicketRepository;
import org.airflyer.com.service.dto.BuyPlaneTicketDTO;
import org.airflyer.com.service.mapper.BuyPlaneTicketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BuyPlaneTicket}.
 */
@Service
@Transactional
public class BuyPlaneTicketService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "BuyPlaneTicket";

    private final Logger log = LoggerFactory.getLogger(BuyPlaneTicketService.class);

    private final ProcessInstanceService processInstanceService;

    private final BuyTicketRepository buyTicketRepository;

    private final BuyPlaneTicketRepository buyPlaneTicketRepository;

    private final BuyPlaneTicketMapper buyPlaneTicketMapper;

    public BuyPlaneTicketService(
        ProcessInstanceService processInstanceService,
        BuyTicketRepository buyTicketRepository,
        BuyPlaneTicketRepository buyPlaneTicketRepository,
        BuyPlaneTicketMapper buyPlaneTicketMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.buyTicketRepository = buyTicketRepository;
        this.buyPlaneTicketRepository = buyPlaneTicketRepository;
        this.buyPlaneTicketMapper = buyPlaneTicketMapper;
    }

    /**
     * Save a buyPlaneTicket.
     *
     * @param buyPlaneTicketDTO the entity to save.
     * @return the persisted entity.
     */
    public BuyPlaneTicketDTO create(BuyPlaneTicketDTO buyPlaneTicketDTO) {
        log.debug("Request to save BuyPlaneTicket : {}", buyPlaneTicketDTO);

        BuyPlaneTicket buyPlaneTicket = buyPlaneTicketMapper.toEntity(buyPlaneTicketDTO);

        //Saving the domainEntity
        buyTicketRepository.save(buyPlaneTicket.getBuyTicket());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "BuyTicket#" + buyPlaneTicket.getBuyTicket().getId(),
            buyPlaneTicket
        );
        buyPlaneTicket.setProcessInstance(processInstance);

        //Saving the process entity
        buyPlaneTicket = buyPlaneTicketRepository.save(buyPlaneTicket);
        return buyPlaneTicketMapper.toDto(buyPlaneTicket);
    }

    /**
     * Get all the buyPlaneTickets.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BuyPlaneTicketDTO> findAll() {
        log.debug("Request to get all BuyPlaneTickets");
        return buyPlaneTicketRepository
            .findAll()
            .stream()
            .map(buyPlaneTicketMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one buyPlaneTicket by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BuyPlaneTicketDTO> findOne(Long id) {
        log.debug("Request to get BuyPlaneTicket : {}", id);
        return buyPlaneTicketRepository.findById(id).map(buyPlaneTicketMapper::toDto);
    }

    /**
     * Get one buyPlaneTicket by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BuyPlaneTicketDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get BuyPlaneTicket by  processInstanceId: {}", processInstanceId);
        return buyPlaneTicketRepository.findByProcessInstanceId(processInstanceId).map(buyPlaneTicketMapper::toDto);
    }
}
