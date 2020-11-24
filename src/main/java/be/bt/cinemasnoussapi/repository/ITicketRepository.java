package be.bt.cinemasnoussapi.repository;

import be.bt.cinemasnoussapi.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketRepository extends JpaRepository<Ticket, Long> {
}
