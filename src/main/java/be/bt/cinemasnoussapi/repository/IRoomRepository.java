package be.bt.cinemasnoussapi.repository;

import be.bt.cinemasnoussapi.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomRepository extends JpaRepository<Room, Long> {
}
