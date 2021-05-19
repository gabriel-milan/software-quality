package org.airflyer.com.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.airflyer.com.domain.BuyTicket;
import org.airflyer.com.repository.BuyTicketRepository;
import org.airflyer.com.service.dto.BuyTicketDTO;
import org.airflyer.com.service.mapper.BuyTicketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BuyTicket}.
 */
@Service
@Transactional
public class BuyTicketService {

    private final Logger log = LoggerFactory.getLogger(BuyTicketService.class);

    private final BuyTicketRepository buyTicketRepository;

    private final BuyTicketMapper buyTicketMapper;

    public BuyTicketService(BuyTicketRepository buyTicketRepository, BuyTicketMapper buyTicketMapper) {
        this.buyTicketRepository = buyTicketRepository;
        this.buyTicketMapper = buyTicketMapper;
    }

    /**
     * Save a buyTicket.
     *
     * @param buyTicketDTO the entity to save.
     * @return the persisted entity.
     */
    public BuyTicketDTO save(BuyTicketDTO buyTicketDTO) {
        log.debug("Request to save BuyTicket : {}", buyTicketDTO);
        BuyTicket buyTicket = buyTicketMapper.toEntity(buyTicketDTO);
        buyTicket = buyTicketRepository.save(buyTicket);
        return buyTicketMapper.toDto(buyTicket);
    }

    /**
     * Partially update a buyTicket.
     *
     * @param buyTicketDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BuyTicketDTO> partialUpdate(BuyTicketDTO buyTicketDTO) {
        log.debug("Request to partially update BuyTicket : {}", buyTicketDTO);

        return buyTicketRepository
            .findById(buyTicketDTO.getId())
            .map(
                existingBuyTicket -> {
                    buyTicketMapper.partialUpdate(existingBuyTicket, buyTicketDTO);
                    return existingBuyTicket;
                }
            )
            .map(buyTicketRepository::save)
            .map(buyTicketMapper::toDto);
    }

    /**
     * Get all the buyTickets.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BuyTicketDTO> findAll() {
        log.debug("Request to get all BuyTickets");
        return buyTicketRepository.findAll().stream().map(buyTicketMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one buyTicket by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BuyTicketDTO> findOne(Long id) {
        log.debug("Request to get BuyTicket : {}", id);
        return buyTicketRepository.findById(id).map(buyTicketMapper::toDto);
    }

    /**
     * Delete the buyTicket by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BuyTicket : {}", id);
        buyTicketRepository.deleteById(id);
    }
}
