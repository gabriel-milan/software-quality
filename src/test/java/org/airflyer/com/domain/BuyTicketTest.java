package org.airflyer.com.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.airflyer.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BuyTicketTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuyTicket.class);
        BuyTicket buyTicket1 = new BuyTicket();
        buyTicket1.setId(1L);
        BuyTicket buyTicket2 = new BuyTicket();
        buyTicket2.setId(buyTicket1.getId());
        assertThat(buyTicket1).isEqualTo(buyTicket2);
        buyTicket2.setId(2L);
        assertThat(buyTicket1).isNotEqualTo(buyTicket2);
        buyTicket1.setId(null);
        assertThat(buyTicket1).isNotEqualTo(buyTicket2);
    }
}
