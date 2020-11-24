package be.bt.cinemasnoussapi.repository;

import be.bt.cinemasnoussapi.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieRepository extends JpaRepository<Movie, Long> {
}
