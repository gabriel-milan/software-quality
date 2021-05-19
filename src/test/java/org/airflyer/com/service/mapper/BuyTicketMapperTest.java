package org.airflyer.com.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuyTicketMapperTest {

    private BuyTicketMapper buyTicketMapper;

    @BeforeEach
    public void setUp() {
        buyTicketMapper = new BuyTicketMapperImpl();
    }
}
