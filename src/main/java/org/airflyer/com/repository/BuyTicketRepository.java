package org.airflyer.com.repository;

import org.airflyer.com.domain.BuyTicket;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BuyTicket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuyTicketRepository extends JpaRepository<BuyTicket, Long> {}
