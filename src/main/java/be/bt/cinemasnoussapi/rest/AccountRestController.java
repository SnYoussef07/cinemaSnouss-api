package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.AppUser;
import be.bt.cinemasnoussapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {
    @Autowired
    private AccountService accountService;

    @PostMapping("register")
    public AppUser register(@RequestBody AppUser user) {
        return accountService.saveuser(user);
    }
}
