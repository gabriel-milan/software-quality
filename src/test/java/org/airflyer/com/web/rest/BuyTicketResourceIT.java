package org.airflyer.com.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.airflyer.com.IntegrationTest;
import org.airflyer.com.domain.BuyTicket;
import org.airflyer.com.repository.BuyTicketRepository;
import org.airflyer.com.service.dto.BuyTicketDTO;
import org.airflyer.com.service.mapper.BuyTicketMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BuyTicketResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BuyTicketResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGEM = "AAAAAAAAAA";
    private static final String UPDATED_ORIGEM = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINO = "AAAAAAAAAA";
    private static final String UPDATED_DESTINO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_IDA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_IDA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATA_VOLTA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_VOLTA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMPANHIA_AEREA = "AAAAAAAAAA";
    private static final String UPDATED_COMPANHIA_AEREA = "BBBBBBBBBB";

    private static final String DEFAULT_CARTAO_CREDITO = "AAAAAAAAAA";
    private static final String UPDATED_CARTAO_CREDITO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/buy-tickets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BuyTicketRepository buyTicketRepository;

    @Autowired
    private BuyTicketMapper buyTicketMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBuyTicketMockMvc;

    private BuyTicket buyTicket;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BuyTicket createEntity(EntityManager em) {
        BuyTicket buyTicket = new BuyTicket()
            .name(DEFAULT_NAME)
            .origem(DEFAULT_ORIGEM)
            .destino(DEFAULT_DESTINO)
            .dataIda(DEFAULT_DATA_IDA)
            .dataVolta(DEFAULT_DATA_VOLTA)
            .companhiaAerea(DEFAULT_COMPANHIA_AEREA)
            .cartaoCredito(DEFAULT_CARTAO_CREDITO);
        return buyTicket;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BuyTicket createUpdatedEntity(EntityManager em) {
        BuyTicket buyTicket = new BuyTicket()
            .name(UPDATED_NAME)
            .origem(UPDATED_ORIGEM)
            .destino(UPDATED_DESTINO)
            .dataIda(UPDATED_DATA_IDA)
            .dataVolta(UPDATED_DATA_VOLTA)
            .companhiaAerea(UPDATED_COMPANHIA_AEREA)
            .cartaoCredito(UPDATED_CARTAO_CREDITO);
        return buyTicket;
    }

    @BeforeEach
    public void initTest() {
        buyTicket = createEntity(em);
    }

    @Test
    @Transactional
    void getAllBuyTickets() throws Exception {
        // Initialize the database
        buyTicketRepository.saveAndFlush(buyTicket);

        // Get all the buyTicketList
        restBuyTicketMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buyTicket.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].origem").value(hasItem(DEFAULT_ORIGEM)))
            .andExpect(jsonPath("$.[*].destino").value(hasItem(DEFAULT_DESTINO)))
            .andExpect(jsonPath("$.[*].dataIda").value(hasItem(DEFAULT_DATA_IDA.toString())))
            .andExpect(jsonPath("$.[*].dataVolta").value(hasItem(DEFAULT_DATA_VOLTA.toString())))
            .andExpect(jsonPath("$.[*].companhiaAerea").value(hasItem(DEFAULT_COMPANHIA_AEREA)))
            .andExpect(jsonPath("$.[*].cartaoCredito").value(hasItem(DEFAULT_CARTAO_CREDITO)));
    }

    @Test
    @Transactional
    void getBuyTicket() throws Exception {
        // Initialize the database
        buyTicketRepository.saveAndFlush(buyTicket);

        // Get the buyTicket
        restBuyTicketMockMvc
            .perform(get(ENTITY_API_URL_ID, buyTicket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(buyTicket.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.origem").value(DEFAULT_ORIGEM))
            .andExpect(jsonPath("$.destino").value(DEFAULT_DESTINO))
            .andExpect(jsonPath("$.dataIda").value(DEFAULT_DATA_IDA.toString()))
            .andExpect(jsonPath("$.dataVolta").value(DEFAULT_DATA_VOLTA.toString()))
            .andExpect(jsonPath("$.companhiaAerea").value(DEFAULT_COMPANHIA_AEREA))
            .andExpect(jsonPath("$.cartaoCredito").value(DEFAULT_CARTAO_CREDITO));
    }

    @Test
    @Transactional
    void getNonExistingBuyTicket() throws Exception {
        // Get the buyTicket
        restBuyTicketMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
