package be.bt.cinemasnoussapi.repository;

import be.bt.cinemasnoussapi.domain.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICinemaRepository extends JpaRepository<Cinema, Long> {
}
