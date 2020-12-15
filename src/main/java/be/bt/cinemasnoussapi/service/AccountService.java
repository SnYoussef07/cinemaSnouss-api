package be.bt.cinemasnoussapi.service;

import be.bt.cinemasnoussapi.domain.AppRole;
import be.bt.cinemasnoussapi.domain.AppUser;

public interface AccountService {
    public AppUser saveuser(AppUser user);

    public AppRole saveRole(AppRole role);

    public void addRoleToUser(String username, String roleName);

    public AppUser findUserByUsername(String username);
}
