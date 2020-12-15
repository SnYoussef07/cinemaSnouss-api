package be.bt.cinemasnoussapi.repository;

import be.bt.cinemasnoussapi.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
}
