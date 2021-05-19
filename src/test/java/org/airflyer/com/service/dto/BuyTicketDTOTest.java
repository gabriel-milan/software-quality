package org.airflyer.com.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.airflyer.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BuyTicketDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuyTicketDTO.class);
        BuyTicketDTO buyTicketDTO1 = new BuyTicketDTO();
        buyTicketDTO1.setId(1L);
        BuyTicketDTO buyTicketDTO2 = new BuyTicketDTO();
        assertThat(buyTicketDTO1).isNotEqualTo(buyTicketDTO2);
        buyTicketDTO2.setId(buyTicketDTO1.getId());
        assertThat(buyTicketDTO1).isEqualTo(buyTicketDTO2);
        buyTicketDTO2.setId(2L);
        assertThat(buyTicketDTO1).isNotEqualTo(buyTicketDTO2);
        buyTicketDTO1.setId(null);
        assertThat(buyTicketDTO1).isNotEqualTo(buyTicketDTO2);
    }
}
