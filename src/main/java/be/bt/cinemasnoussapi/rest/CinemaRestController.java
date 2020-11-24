package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.repository.ICinemaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinemas")
@CrossOrigin
public class CinemaRestController {

    private ICinemaRepository cinemaRepository;

    public CinemaRestController(ICinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }
}
