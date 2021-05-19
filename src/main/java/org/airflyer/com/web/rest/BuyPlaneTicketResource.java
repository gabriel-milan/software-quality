package org.airflyer.com.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.airflyer.com.service.BuyPlaneTicketService;
import org.airflyer.com.service.dto.BuyPlaneTicketDTO;
import org.airflyer.com.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.airflyer.com.domain.BuyPlaneTicket}.
 */
@RestController
@RequestMapping("/api")
public class BuyPlaneTicketResource {

    private final Logger log = LoggerFactory.getLogger(BuyPlaneTicketResource.class);

    private static final String ENTITY_NAME = "buyPlaneTicket";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BuyPlaneTicketService buyPlaneTicketService;

    public BuyPlaneTicketResource(BuyPlaneTicketService buyPlaneTicketService) {
        this.buyPlaneTicketService = buyPlaneTicketService;
    }

    /**
     * {@code POST  /buy-plane-tickets} : Create a new buyPlaneTicket.
     *
     * @param buyPlaneTicketDTO the buyPlaneTicketDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new buyPlaneTicketDTO, or with status {@code 400 (Bad Request)} if the buyPlaneTicket has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/buy-plane-tickets")
    public ResponseEntity<BuyPlaneTicketDTO> create(@RequestBody BuyPlaneTicketDTO buyPlaneTicketDTO) throws URISyntaxException {
        log.debug("REST request to save BuyPlaneTicket : {}", buyPlaneTicketDTO);
        if (buyPlaneTicketDTO.getId() != null) {
            throw new BadRequestAlertException("A new buyPlaneTicket cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BuyPlaneTicketDTO result = buyPlaneTicketService.create(buyPlaneTicketDTO);
        return ResponseEntity
            .created(new URI("/api/buy-plane-tickets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /buy-plane-tickets} : get all the buyPlaneTickets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of buyPlaneTickets in body.
     */
    @GetMapping("/buy-plane-tickets")
    public List<BuyPlaneTicketDTO> getAllBuyPlaneTickets() {
        log.debug("REST request to get all BuyPlaneTickets");
        return buyPlaneTicketService.findAll();
    }

    /**
     * {@code GET  /buy-plane-tickets/:id} : get the "id" buyPlaneTicket.
     *
     * @param processInstanceId the id of the buyPlaneTicketDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buyPlaneTicketDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/buy-plane-tickets/{processInstanceId}")
    public ResponseEntity<BuyPlaneTicketDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get BuyPlaneTicket by processInstanceId : {}", processInstanceId);
        Optional<BuyPlaneTicketDTO> buyPlaneTicketDTO = buyPlaneTicketService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(buyPlaneTicketDTO);
    }
}
