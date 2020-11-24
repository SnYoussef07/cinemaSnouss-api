package be.bt.cinemasnoussapi.repository;

import be.bt.cinemasnoussapi.domain.FilmScreening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFilmScreeningRepository extends JpaRepository<FilmScreening, Long> {
}
