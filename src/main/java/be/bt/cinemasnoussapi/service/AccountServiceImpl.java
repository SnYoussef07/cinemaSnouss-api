package be.bt.cinemasnoussapi.service;

import be.bt.cinemasnoussapi.domain.AppRole;
import be.bt.cinemasnoussapi.domain.AppUser;
import be.bt.cinemasnoussapi.repository.IAppRoleRepository;
import be.bt.cinemasnoussapi.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IAppUserRepository iAppUserRepository;

    @Autowired
    private IAppRoleRepository iAppRoleRepository;

    @Override
    public AppUser saveuser(AppUser user) {
        String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        return iAppUserRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return iAppRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppRole role = iAppRoleRepository.findByRoleName(roleName);
        AppUser user = iAppUserRepository.findByUsername(username);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return iAppUserRepository.findByUsername(username);
    }
}
