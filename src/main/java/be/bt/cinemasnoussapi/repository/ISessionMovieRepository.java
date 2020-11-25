package be.bt.cinemasnoussapi.repository;

import be.bt.cinemasnoussapi.domain.SessionMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISessionMovieRepository extends JpaRepository<SessionMovie, Long> {
}
