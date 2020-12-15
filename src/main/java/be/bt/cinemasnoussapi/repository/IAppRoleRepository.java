package be.bt.cinemasnoussapi.repository;

import be.bt.cinemasnoussapi.domain.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppRoleRepository extends JpaRepository<AppRole, Long> {
    public AppRole findByRoleName(String roleName);
}
