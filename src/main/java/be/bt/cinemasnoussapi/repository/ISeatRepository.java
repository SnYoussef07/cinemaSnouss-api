package be.bt.cinemasnoussapi.repository;

import be.bt.cinemasnoussapi.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISeatRepository extends JpaRepository<Seat, Long> {
}
