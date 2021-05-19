package org.airflyer.com.repository;

import java.util.Optional;
import org.airflyer.com.domain.BuyPlaneTicket;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BuyPlaneTicket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuyPlaneTicketRepository extends JpaRepository<BuyPlaneTicket, Long> {
    Optional<BuyPlaneTicket> findByProcessInstanceId(Long processInstanceId);
}
