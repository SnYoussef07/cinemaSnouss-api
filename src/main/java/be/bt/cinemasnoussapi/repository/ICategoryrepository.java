package be.bt.cinemasnoussapi.repository;

import be.bt.cinemasnoussapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryrepository extends JpaRepository<Category, Long> {
}
