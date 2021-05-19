package org.airflyer.com.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.airflyer.com.repository.BuyTicketRepository;
import org.airflyer.com.service.BuyTicketService;
import org.airflyer.com.service.dto.BuyTicketDTO;
import org.airflyer.com.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.airflyer.com.domain.BuyTicket}.
 */
@RestController
@RequestMapping("/api")
public class BuyTicketResource {

    private final Logger log = LoggerFactory.getLogger(BuyTicketResource.class);

    private final BuyTicketService buyTicketService;

    private final BuyTicketRepository buyTicketRepository;

    public BuyTicketResource(BuyTicketService buyTicketService, BuyTicketRepository buyTicketRepository) {
        this.buyTicketService = buyTicketService;
        this.buyTicketRepository = buyTicketRepository;
    }

    /**
     * {@code GET  /buy-tickets} : get all the buyTickets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of buyTickets in body.
     */
    @GetMapping("/buy-tickets")
    public List<BuyTicketDTO> getAllBuyTickets() {
        log.debug("REST request to get all BuyTickets");
        return buyTicketService.findAll();
    }

    /**
     * {@code GET  /buy-tickets/:id} : get the "id" buyTicket.
     *
     * @param id the id of the buyTicketDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buyTicketDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/buy-tickets/{id}")
    public ResponseEntity<BuyTicketDTO> getBuyTicket(@PathVariable Long id) {
        log.debug("REST request to get BuyTicket : {}", id);
        Optional<BuyTicketDTO> buyTicketDTO = buyTicketService.findOne(id);
        return ResponseUtil.wrapOrNotFound(buyTicketDTO);
    }
}
